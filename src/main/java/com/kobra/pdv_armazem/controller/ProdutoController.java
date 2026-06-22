package com.kobra.pdv_armazem.controller;

import com.kobra.pdv_armazem.entity.Produto;
import com.kobra.pdv_armazem.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("title", "Produtos - Comercial Reginha");
        model.addAttribute("pageTitle", "Produtos");
        model.addAttribute("produtos", produtoService.listarTodos());
        model.addAttribute("content", "produtos :: content");
        return "produtos";
    }

    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("title", "Novo Produto - Comercial Reginha");
        model.addAttribute("pageTitle", "Novo Produto");
        model.addAttribute("produto", new Produto());
        model.addAttribute("content", "produtos/produto-form :: content");
        return "produtos/produto-form";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Editar Produto - Comercial Reginha");
        model.addAttribute("pageTitle", "Editar Produto");
        model.addAttribute("produto", produtoService.buscarPorId(id));
        model.addAttribute("content", "produtos/produto-form :: content");
        return "produtos/produto-form";
    }

    @PostMapping
    public String salvarProduto(@ModelAttribute Produto produto, RedirectAttributes redirectAttributes) {
        produtoService.salvar(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        produtoService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
        return "redirect:/produtos";
    }
}