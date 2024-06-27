package com.br.fatec.inventarioproduto.mapper;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Fornecedor;
import com.br.fatec.inventarioproduto.model.Movimentacao;
import com.br.fatec.inventarioproduto.model.Produto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovimentacaoMapper {

    MovimentacaoMapper INSTANCE = Mappers.getMapper(MovimentacaoMapper.class);

    @Mapping(source = "fornecedorId", target = "fornecedor", qualifiedByName = "mapFornecedor")
    @Mapping(source = "produtoId", target = "produto", qualifiedByName = "mapProduto")
    @Mapping(source = "tipoMovimentacao", target = "tipo_movimentacao")
    Movimentacao toEntity(MovimentacaoDTO dto);

    @Mapping(source = "fornecedor", target = "fornecedorId", qualifiedByName = "mapFornecedorId")
    @Mapping(source = "produto", target = "produtoId", qualifiedByName = "mapProdutoId")
    @Mapping(source = "tipo_movimentacao", target = "tipoMovimentacao")
    MovimentacaoDTO toDTO(Movimentacao entity);

    @Named("mapFornecedor")
    default Fornecedor mapFornecedor(Long value) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(value);
        return fornecedor;
    }

    @Named("mapProduto")
    default Produto mapProduto(Long value) {
        Produto produto = new Produto();
        produto.setId(value);
        return produto;
    }

    @Named("mapFornecedorId")
    default Long mapFornecedorId(Fornecedor fornecedor) {
        return fornecedor.getId();
    }

    @Named("mapProdutoId")
    default Long mapProdutoId(Produto produto) {
        return produto.getId();
    }
}
