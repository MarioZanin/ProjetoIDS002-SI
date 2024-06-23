package com.br.fatec.inventarioproduto.service;
import java.util.List;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;

@SuppressWarnings("unused")
public interface IService<T> {
	T create(T obj);

	T findById(Long id);

	List<T> findAll();

	boolean update(Long id, T obj);

	boolean delete(Long id);
} 