package com.kobra.pdv_armazem.controller;

import com.kobra.pdv_armazem.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final ProdutoService produtoService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard - Comercial Reginha");
        model.addAttribute("pageTitle", "Dashboard");

        // Dados para o dashboard
        model.addAttribute("totalProdutos", produtoService.countTotalProdutos());
        model.addAttribute("totalValorEstoque", produtoService.calcularValorTotalEstoque());
        model.addAttribute("produtosBaixoEstoque", produtoService.countProdutosBaixoEstoque());
        model.addAttribute("produtosCriticos", produtoService.findProdutosCriticos(5));

        // TODO: Implementar futuramente com VendaService
        model.addAttribute("vendasHoje", 12);

        return "dashboard/dashboard";
    }
}