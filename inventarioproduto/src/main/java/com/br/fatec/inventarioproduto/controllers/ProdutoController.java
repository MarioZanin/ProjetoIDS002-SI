package com.br.fatec.inventarioproduto.controllers;

import com.br.fatec.inventarioproduto.model.FullValidationGroup;
import com.br.fatec.inventarioproduto.model.PartialValidationGroup;
import com.br.fatec.inventarioproduto.model.Produto;
import com.br.fatec.inventarioproduto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
@Tag(name = "Produtos", description = "Gerenciar Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna a lista de produtos", description = "Obtem Lista de Produtos com todas as informações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/paginated", produces = "application/json")
    @Operation(summary = "Retorna a lista de produtos paginada", description = "Obtem Lista de Produtos com todas as informações paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Page<Produto>> getAllPaginated(Pageable pageable) {
        Page<Produto> produtos = service.findAll(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Retorna um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Produto> get(@PathVariable("id") Long id) {
        Produto produto = service.findById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Produto> post(@RequestBody @Validated(FullValidationGroup.class) Produto produto) {
        Produto createdProduto = service.create(produto);
        URI location = URI.create(String.format("/api/produto/%s", createdProduto.getId()));
        return ResponseEntity.created(location).body(createdProduto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Boolean> put(@PathVariable("id") Long id, @RequestBody @Validated(FullValidationGroup.class) Produto produto) {
        produto.setId(id);
        boolean updatedProduto = service.update(produto);
        if (updatedProduto) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto parcialmente atualizado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Produto> patch(@PathVariable("id") Long id, @RequestBody @Validated(PartialValidationGroup.class) Produto produto) {
        Produto existingProduto = service.findById(id);
        if (existingProduto == null) {
            return ResponseEntity.notFound().build();
        }

        if (produto.getNm_nome() != null) {
            existingProduto.setNm_nome(produto.getNm_nome());
        }
        if (produto.getQt_Minima() != null) {
            existingProduto.setQt_Minima(produto.getQt_Minima());
        }
        if (produto.getQt_Maxima() != null) {
            existingProduto.setQt_Maxima(produto.getQt_Maxima());
        }
        if (produto.getPr_preco() != null) {
            existingProduto.setPr_preco(produto.getPr_preco());
        }
        if (produto.getCt_categoria() != null) {
            existingProduto.setCt_categoria(produto.getCt_categoria());
        }
        if (produto.getQt_quantidade() != null) {
            existingProduto.setQt_quantidade(produto.getQt_quantidade());
        }

        Produto patchedProduto = service.updatePartial(existingProduto);
        return ResponseEntity.ok(patchedProduto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
