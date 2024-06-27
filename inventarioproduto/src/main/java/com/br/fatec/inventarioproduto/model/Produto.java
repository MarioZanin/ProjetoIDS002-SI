package com.br.fatec.inventarioproduto.model;

import lombok.Data;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {FullValidationGroup.class, PartialValidationGroup.class})
    private String nm_nome;

    @NotNull(groups = FullValidationGroup.class)
    @Null(groups = PartialValidationGroup.class)
    @Positive(groups = FullValidationGroup.class)
    private Integer qt_Minima;

    @NotNull(groups = FullValidationGroup.class)
    @Null(groups = PartialValidationGroup.class)
    @Positive(groups = FullValidationGroup.class)
    private Integer qt_Maxima;

    @NotNull(groups = FullValidationGroup.class)
    @Null(groups = PartialValidationGroup.class)
    private BigDecimal pr_preco;

    @NotBlank(groups = FullValidationGroup.class)
    @Null(groups = PartialValidationGroup.class)
    private String ct_categoria;

    @NotNull(groups = FullValidationGroup.class)
    @Null(groups = PartialValidationGroup.class)
    @Positive(groups = FullValidationGroup.class)
    private Integer qt_quantidade;

    // Getter and Setter for id
    public Long getId() {
        return id;
    }
}
