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
    @GetMapping(value = "/paginated", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Retorna a lista de produtos paginada", description = "Obtem Lista de Produtos com todas as informações paginadas")
    public ResponseEntity<Page<Produto>> getAllPaginated(Pageable pageable) {
        Page<Produto> produtos = service.findAll(pageable);
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
    public ResponseEntity<Produto> post(@RequestBody @Validated(FullValidationGroup.class) Produto produto) {
        Produto createdProduto = service.create(produto);
        URI location = URI.create(String.format("/api/produto/%s", createdProduto.getId()));
        return ResponseEntity.created(location).body(createdProduto);
    }
    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Boolean> put(@RequestBody @Validated(FullValidationGroup.class) Produto produto) {
        boolean updatedProduto = service.update(produto);
        if (updatedProduto) {
            return ResponseEntity.ok(updatedProduto);
        }
        return ResponseEntity.notFound().build();
    }
    @Override
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um produto")
    public ResponseEntity<Produto> patch(@PathVariable("id") Long id, @RequestBody @Validated(PartialValidationGroup.class) Produto produto) {
        produto.setId(id);
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
