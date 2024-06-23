package com.br.fatec.inventarioproduto.controllers;

import com.br.fatec.inventarioproduto.model.Produto;
import com.br.fatec.inventarioproduto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
@Tag(name = "Produtos", description = "Gerenciar Produtos")
public class ProdutoController implements IController<Produto> {

    @Autowired
    private ProdutoService service;

    @Override
    @GetMapping(produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Retorna a lista de produtos", description = "Obtem Lista de Produtos com todas as informações")
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Produto> get(@PathVariable("id") Long id) {
        Produto produto = service.findById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    @Operation(summary = "Cria um produto")
    public ResponseEntity<Produto> post(@RequestBody @Valid Produto produto) {
        Produto createdProduto = service.create(produto);
        URI location = URI.create(String.format("/api/produto/%s", createdProduto.getId()));
        return ResponseEntity.created(location).body(createdProduto);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Boolean> put(@RequestBody @Valid Produto produto) {
        boolean updatedProduto = service.update(produto);
        if (updatedProduto) {
            return ResponseEntity.ok(updatedProduto);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um produto")
    public ResponseEntity<Produto> patch(@RequestBody @Valid Produto produto) {
        Produto patchedProduto = service.updatePartial(produto);
        if (patchedProduto != null) {
            return ResponseEntity.ok(patchedProduto);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Exclui um produto")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
