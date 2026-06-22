package com.kobra.pdv_armazem.dto;

import java.math.BigDecimal;

public record DashboardDTO(
        long totalProdutos,
        long estoqueBaixo,
        long semEstoque,
        BigDecimal valorEstoque
) {
}
