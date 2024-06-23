package com.br.fatec.inventarioproduto.repository;

import com.br.fatec.inventarioproduto.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
