package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Movimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovimentacaoService {
    List<Movimentacao> findAll();
    Movimentacao findById(Long id);
    Movimentacao create(MovimentacaoDTO movimentacaoDTO);
    boolean update(Movimentacao movimentacao);
    boolean updatePartial(Movimentacao movimentacao);
    boolean delete(Long id);
    Page<Movimentacao> findAll(Pageable pageable);
}
