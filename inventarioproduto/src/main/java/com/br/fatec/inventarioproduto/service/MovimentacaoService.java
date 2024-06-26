package com.br.fatec.inventarioproduto.service;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Fornecedor;
import com.br.fatec.inventarioproduto.model.Movimentacao;
import com.br.fatec.inventarioproduto.model.Produto;
import com.br.fatec.inventarioproduto.repository.MovimentacaoRepository;
import com.br.fatec.inventarioproduto.repository.ProdutoRepository;
import com.br.fatec.inventarioproduto.repository.FornecedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    public Page<Movimentacao> findAll(Pageable pageable) {
        return movimentacaoRepository.findAll(pageable);
    }

    public Movimentacao findById(Long id) {
        return movimentacaoRepository.findById(id).orElse(null);
    }

    public Movimentacao create(MovimentacaoDTO movimentacaoDTO) {
        Produto produto = produtoRepository.findById(movimentacaoDTO.getProdutoId()).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        Fornecedor fornecedor = fornecedorRepository.findById(movimentacaoDTO.getFornecedorId()).orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));

        Movimentacao movimentacao = new Movimentacao();
        BeanUtils.copyProperties(movimentacaoDTO, movimentacao);
        movimentacao.setProduto(produto);
        movimentacao.setFornecedor(fornecedor);

        return movimentacaoRepository.save(movimentacao);
    }

    public boolean update(Movimentacao movimentacao) {
        if (movimentacaoRepository.existsById(movimentacao.getId())) {
            movimentacaoRepository.save(movimentacao);
            return true;
        }
        return false;
    }

    public boolean updatePartial(Movimentacao movimentacao) {
        Optional<Movimentacao> existingMovimentacao = movimentacaoRepository.findById(movimentacao.getId());
        if (existingMovimentacao.isPresent()) {
            Movimentacao movToUpdate = existingMovimentacao.get();
            BeanUtils.copyProperties(movimentacao, movToUpdate, getNullPropertyNames(movimentacao));
            movimentacaoRepository.save(movToUpdate);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (movimentacaoRepository.existsById(id)) {
            movimentacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapperImpl src = new BeanWrapperImpl(source);
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

