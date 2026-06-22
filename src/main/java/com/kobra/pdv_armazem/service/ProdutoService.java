package com.kobra.pdv_armazem.service;

import com.kobra.pdv_armazem.entity.Produto;
import com.kobra.pdv_armazem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // ===================== MÉTODOS PARA O DASHBOARD =====================

    public long countTotalProdutos() {
        return repository.count();
    }

    public BigDecimal calcularValorTotalEstoque() {
        return repository.findAll().stream()
                .map(p -> p.getPrecoCompra() != null && p.getEstoqueAtual() != null ?
                        p.getPrecoCompra().multiply(p.getEstoqueAtual()) : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public long countProdutosBaixoEstoque() {
        return repository.findAll().stream()
                .filter(p -> p.getEstoqueAtual() != null && p.getEstoqueMinimo() != null &&
                        p.getEstoqueAtual().compareTo(p.getEstoqueMinimo()) <= 0)
                .count();
    }

    public List<Produto> findProdutosCriticos(int limite) {
        return repository.findAll().stream()
                .filter(p -> p.getEstoqueAtual() != null && p.getEstoqueMinimo() != null &&
                        p.getEstoqueAtual().compareTo(p.getEstoqueMinimo()) <= 0)
                .limit(limite)
                .toList();
    }

    // Método auxiliar (opcional)
    public List<Produto> listarComBaixoEstoque() {
        return findProdutosCriticos(10);
    }
}