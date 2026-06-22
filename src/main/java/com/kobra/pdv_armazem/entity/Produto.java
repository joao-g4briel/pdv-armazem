package com.kobra.pdv_armazem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nome;

    private String categoria;

    private String unidade;

    private BigDecimal precoCompra;

    private BigDecimal precoVenda;

    private BigDecimal estoqueAtual;

    private BigDecimal estoqueMinimo;
}
