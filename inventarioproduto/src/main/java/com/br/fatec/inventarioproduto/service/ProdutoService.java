package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Produto;
import com.br.fatec.inventarioproduto.model.TipoMovimentacao;
import com.br.fatec.inventarioproduto.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Page<Produto> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public boolean update(Produto produto) {
        if (repository.existsById(produto.getId())) {
            repository.save(produto);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Produto movimentarProduto(MovimentacaoDTO movimentacaoDTO) {
        Produto produto = findById(movimentacaoDTO.getProdutoId());
        if (produto != null) {
            if (movimentacaoDTO.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
                produto.setQt_quantidade(produto.getQt_quantidade() + movimentacaoDTO.getQuantidade());
            } else if (movimentacaoDTO.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
                produto.setQt_quantidade(produto.getQt_quantidade() - movimentacaoDTO.getQuantidade());
            }
            return repository.save(produto);
        }
        return null;
    }

    public Produto updatePartial(Produto produto) {
        Produto existingProduto = repository.findById(produto.getId()).orElse(null);
        if (existingProduto != null) {
            copyNonNullProperties(produto, existingProduto);
            return repository.save(existingProduto);
        }
        return null;
    }

    private void copyNonNullProperties(Produto src, Produto target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

