package com.kobra.pdv_armazem.service;

import com.kobra.pdv_armazem.dto.DashboardDTO;
import com.kobra.pdv_armazem.entity.Produto;
import com.kobra.pdv_armazem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ProdutoRepository produtoRepository;

    public DashboardDTO obterIndicadores() {

        List<Produto> produtos = produtoRepository.findAll();

        long totalProdutos = produtos.size();

        long estoqueBaixo = produtos.stream()
                .filter(p -> p.getEstoqueAtual()
                        .compareTo(p.getEstoqueMinimo()) <= 0)
                .count();

        long semEstoque = produtos.stream()
                .filter(p -> p.getEstoqueAtual()
                        .compareTo(BigDecimal.ZERO) <= 0)
                .count();

        BigDecimal valorEstoque = produtos.stream()
                .map(p ->
                        p.getPrecoCompra()
                                .multiply(p.getEstoqueAtual())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DashboardDTO(
                totalProdutos,
                estoqueBaixo,
                semEstoque,
                valorEstoque
        );
    }
}
