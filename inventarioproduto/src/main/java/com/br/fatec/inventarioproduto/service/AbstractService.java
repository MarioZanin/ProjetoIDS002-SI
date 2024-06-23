package com.br.fatec.inventarioproduto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.AbstractEntity;
import java.util.List;

public abstract class AbstractService<T> extends AbstractEntity{

    @Autowired
    protected JpaRepository<T, Long> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    @SuppressWarnings("unchecked")
    public List<MovimentacaoDTO> findAll() {
        return (List<MovimentacaoDTO>) repository.findAll();
    }

    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
