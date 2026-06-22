package com.kobra.pdv_armazem.controller;

import com.kobra.pdv_armazem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute(
                "dashboard",
                dashboardService.obterIndicadores()
        );

        return "dashboard/dashboard";
    }

}
