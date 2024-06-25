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

    @NotBlank(groups = {FullValidationGroup.class})
    @Size(max = 100, groups = {FullValidationGroup.class, PartialValidationGroup.class})
    private String nm_fornecedor;

    @NotBlank(groups = {FullValidationGroup.class})
    @Pattern(regexp = "\\d{14}", groups = {FullValidationGroup.class, PartialValidationGroup.class})
    private String cd_cnpj;

    @NotBlank(groups = {FullValidationGroup.class})
    @Size(max = 255, groups = {FullValidationGroup.class, PartialValidationGroup.class})
    private String endereco;

    private LocalDateTime data_entrada;
}
