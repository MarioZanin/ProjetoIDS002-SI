package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Produto;
import com.br.fatec.inventarioproduto.model.TipoMovimentacao;
import com.br.fatec.inventarioproduto.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
		//Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updatePartial'");
	}
}
