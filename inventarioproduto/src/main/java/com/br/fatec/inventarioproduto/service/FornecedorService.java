package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.model.Fornecedor;
import com.br.fatec.inventarioproduto.repository.FornecedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Page<Fornecedor> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Fornecedor findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Fornecedor create(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public boolean update(Fornecedor fornecedor) {
        if (repository.existsById(fornecedor.getId())) {
            repository.save(fornecedor);
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

    public Fornecedor updatePartial(Fornecedor fornecedor) {
        Fornecedor existingFornecedor = repository.findById(fornecedor.getId()).orElse(null);
        if (existingFornecedor != null) {
            copyNonNullProperties(fornecedor, existingFornecedor);
            return repository.save(existingFornecedor);
        }
        return null;
    }

    private void copyNonNullProperties(Fornecedor src, Fornecedor target) {
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
