package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.model.Fornecedor;
import com.br.fatec.inventarioproduto.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> findAll() {
        return repository.findAll();
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
        //Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePartial'");
    }
}
