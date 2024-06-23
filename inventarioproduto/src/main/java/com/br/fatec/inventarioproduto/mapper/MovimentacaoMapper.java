package com.br.fatec.inventarioproduto.mapper;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Movimentacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovimentacaoMapper {
    MovimentacaoMapper INSTANCE = Mappers.getMapper(MovimentacaoMapper.class);

    Movimentacao toEntity(MovimentacaoDTO dto);

    MovimentacaoDTO toDTO(List<MovimentacaoDTO> movimentacoes);

    List<MovimentacaoDTO> toDTOList(List<Movimentacao> entities);
}
