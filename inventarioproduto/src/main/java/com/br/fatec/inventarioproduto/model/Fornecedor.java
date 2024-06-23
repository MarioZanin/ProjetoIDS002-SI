package com.br.fatec.inventarioproduto.model;

import lombok.Data;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Data
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nm_fornecedor;

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    private String cd_cnpj;

    @NotBlank
    @Size(max = 255)
    private String endereco;

    private LocalDateTime data_entrada;
}
