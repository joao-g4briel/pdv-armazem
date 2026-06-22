package com.kobra.pdv_armazem.controller;

import com.kobra.pdv_armazem.entity.Produto;
import com.kobra.pdv_armazem.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", service.listarTodos());
        model.addAttribute("title", "Produtos - Comercial Reginha");
        model.addAttribute("pageTitle", "Produtos");
        return "produtos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("title", "Novo Produto - Comercial Reginha");
        model.addAttribute("pageTitle", "Novo Produto");
        return "produto-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = service.buscarPorId(id);
        if (produto == null) {
            return "redirect:/produtos";
        }
        model.addAttribute("produto", produto);
        model.addAttribute("title", "Editar Produto - Comercial Reginha");
        model.addAttribute("pageTitle", "Editar Produto");
        return "produto-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        service.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}