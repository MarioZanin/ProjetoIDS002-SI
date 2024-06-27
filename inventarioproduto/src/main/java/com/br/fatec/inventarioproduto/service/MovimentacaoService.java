package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.mapper.MovimentacaoMapper;
import com.br.fatec.inventarioproduto.model.Movimentacao;
import com.br.fatec.inventarioproduto.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

import java.util.HashSet;

@Service
public class MovimentacaoService {
    @Autowired
    private final MovimentacaoMapper mapper;
    @Autowired
    private final MovimentacaoRepository repository;
   
    public MovimentacaoService(MovimentacaoMapper mapper, MovimentacaoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<Movimentacao> findAll() {
        return repository.findAll();
    }
    
    public Page<Movimentacao> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Movimentacao findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Movimentacao create(MovimentacaoDTO movimentacaodto) {
        Movimentacao movimentacao = mapper.toEntity(movimentacaodto);
        return repository.save(movimentacao);
    }

    public boolean update(Movimentacao movimentacao) {
        if (repository.existsById(movimentacao.getId())) {
            repository.save(movimentacao);
            return true;
        } else {
            return false;
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public boolean updatePartial(Movimentacao movimentacao) {
        if (repository.existsById(movimentacao.getId())) {
            Movimentacao existingMovimentacao = repository.findById(movimentacao.getId()).get();
            if (movimentacao.getData_movimentacao() != null) {
                existingMovimentacao.setData_movimentacao(movimentacao.getData_movimentacao());
            }
            // Atualizar outros campos de forma parcial, se necessário
            repository.save(existingMovimentacao);
            return true;
        }
        return false;
    }

    public static void myCopyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[0]);
    }
}