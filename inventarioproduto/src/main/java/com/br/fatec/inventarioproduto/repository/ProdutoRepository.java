package com.br.fatec.inventarioproduto.repository;

import com.br.fatec.inventarioproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}