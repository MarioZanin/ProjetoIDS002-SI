package com.br.fatec.inventarioproduto.dto;

import lombok.Data;

@Data
public class MovimentacaoDTO {
    private Long id;
    private Long fornecedorId;
    private Long produtoId;
    private String tipoMovimentacao;
    public Integer quantidade;
    
}