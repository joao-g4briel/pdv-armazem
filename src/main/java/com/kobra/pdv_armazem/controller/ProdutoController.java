package com.kobra.pdv_armazem.controller;

import com.kobra.pdv_armazem.entity.Produto;
import com.kobra.pdv_armazem.repository.ProdutoRepository;
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
    private final ProdutoRepository produtoRepository;

    @GetMapping
    public String listar(Model model){

        model.addAttribute(
                "produtos",
                service.listarTodos()
        );

        return "produtos";
    }

    @GetMapping("/novo")
    public String novo(Model model){

        model.addAttribute(
                "produto",
                new Produto()
        );

        return "produto-form";
    }

    @PostMapping
    public String salvar(
            @ModelAttribute Produto produto){

        service.salvar(produto);

        return "redirect:/produtos";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluir(@PathVariable Long id){

        produtoRepository.deleteById(id);

        return "redirect:/produtos";
    }

}
