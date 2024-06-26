package com.br.fatec.inventarioproduto.controllers;

import com.br.fatec.inventarioproduto.dto.MovimentacaoDTO;
import com.br.fatec.inventarioproduto.model.Movimentacao;
import com.br.fatec.inventarioproduto.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacao")
@Tag(name = "Movimentações", description = "Gerenciar Movimentações")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @GetMapping(produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Retorna a lista de movimentações", description = "Obtem Lista de Movimentações com todas as informações")
    public ResponseEntity<List<Movimentacao>> getAll() {
        List<Movimentacao> movimentacoes = service.findAll();
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Movimentacao> get(@PathVariable("id") Long id) {
        Movimentacao movimentacao = service.findById(id);
        if (movimentacao != null) {
            return ResponseEntity.ok(movimentacao);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Cria uma movimentação")
    public ResponseEntity<Movimentacao> post(@RequestBody @Valid MovimentacaoDTO movimentacaoDTO) {
        Movimentacao movimentacao = service.create(movimentacaoDTO);
        URI location = URI.create(String.format("/api/movimentacao/%s", movimentacao.getId()));
        return ResponseEntity.created(location).body(movimentacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma movimentação")
    public ResponseEntity<Boolean> put(@RequestBody @Valid Movimentacao movimentacao) {
        boolean updatedMovimentacao = service.update(movimentacao);
        if (updatedMovimentacao) {
            return ResponseEntity.ok(updatedMovimentacao);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente uma movimentação")
    public ResponseEntity<Boolean> patch(@RequestBody @Valid Movimentacao movimentacao) {
        boolean patchedMovimentacao = service.updatePartial(movimentacao);
        if (patchedMovimentacao) {
            return ResponseEntity.ok(patchedMovimentacao);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Exclui uma movimentação")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
