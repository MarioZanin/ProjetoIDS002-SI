package com.br.fatec.inventarioproduto.model;

import lombok.Data;

import java.math.BigDecimal;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nm_nome;

    @NotNull
    @Positive
    private int qt_Minima;

    @NotNull
    @Positive
    private int qt_Maxima;

    @NotNull
    private BigDecimal pr_preco;

    @NotBlank
    private String ct_categoria;

    @NotNull
    @Positive
    private int qt_quantidade;
}
