package com.br.fatec.inventarioproduto.dto;

import com.br.fatec.inventarioproduto.model.TipoMovimentacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoDTO {
    private Long id;

    @NotNull
    private Long produtoId;

    @NotNull
    private Long fornecedorId;

    @NotNull
    private LocalDateTime dataMovimentacao;

    @NotNull
    @Positive
    private int quantidade;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;
}
