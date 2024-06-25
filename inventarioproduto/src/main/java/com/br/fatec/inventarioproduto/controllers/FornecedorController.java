package com.br.fatec.inventarioproduto.controllers;

import com.br.fatec.inventarioproduto.model.Fornecedor;
import com.br.fatec.inventarioproduto.model.PartialValidationGroup;
import com.br.fatec.inventarioproduto.service.FornecedorService;
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
@RequestMapping("/api/fornecedor")
@Tag(name = "Fornecedores", description = "Gerenciar Fornecedores")
public class FornecedorController implements IController<Fornecedor> {

    @Autowired
    private FornecedorService service;

    @Override
    @GetMapping(produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Retorna a lista de fornecedores", description = "Obtem Lista de Fornecedores com todas as informações")
    public ResponseEntity<List<Fornecedor>> getAll() {
        List<Fornecedor> fornecedores = service.findAll();
        return ResponseEntity.ok(fornecedores);
    }
 
    @GetMapping(value = "/paginated", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Retorna a lista de fornecedores paginada", description = "Obtem Lista de Forneceores com todas as informações paginadas")
    public ResponseEntity<Page<Fornecedor>> getAllPaginated(Pageable pageable) {
        Page<Fornecedor> fornecedores = service.findAll(pageable);
        return ResponseEntity.ok(fornecedores);
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Fornecedor> get(@PathVariable("id") Long id) {
        Fornecedor fornecedor = service.findById(id);
        if (fornecedor != null) {
            return ResponseEntity.ok(fornecedor);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    @Operation(summary = "Cria um fornecedor")
    public ResponseEntity<Fornecedor> post(@RequestBody @Validated(FullValidationGroup.class) Fornecedor fornecedor) {
        Fornecedor createdFornecedor = service.create(fornecedor);
        URI location = URI.create(String.format("/api/fornecedor/%s", createdFornecedor.getId()));
        return ResponseEntity.created(location).body(createdFornecedor);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um fornecedor")
    public ResponseEntity<Boolean> put(@RequestBody @Validated(FullValidationGroup.class) Fornecedor fornecedor) {
        boolean updatedFornecedor = service.update(fornecedor);
        if (updatedFornecedor) {
            return ResponseEntity.ok(updatedFornecedor);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um fornecedor")
    public ResponseEntity<Fornecedor> patch(@PathVariable("id") Long id, @RequestBody @Validated(PartialValidationGroup.class) Fornecedor fornecedor) {
        fornecedor.setId(id);
        Fornecedor patchedFornecedor = service.updatePartial(fornecedor);
        if (patchedFornecedor != null) {
            return ResponseEntity.ok(patchedFornecedor);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Exclui um fornecedor")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
