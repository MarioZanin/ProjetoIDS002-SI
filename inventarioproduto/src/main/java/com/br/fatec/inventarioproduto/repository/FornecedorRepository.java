package com.br.fatec.inventarioproduto.repository;

import com.br.fatec.inventarioproduto.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
