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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/paginated", produces = "application/json")
    @Operation(summary = "Retorna a lista de movimentações paginada", description = "Obtém Lista de Movimentações com todas as informações paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Page<Movimentacao>> getAllPaginated(Pageable pageable) {
        Page<Movimentacao> movimentacao = service.findAll(pageable);
        return ResponseEntity.ok(movimentacao);
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
    public ResponseEntity<Movimentacao> put(@PathVariable("id") Long id, @RequestBody Movimentacao movimentacao) {
        movimentacao.setId(id);
        boolean updated = service.update(movimentacao);
        if (updated) {
            return ResponseEntity.ok(movimentacao);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma movimentação")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente uma movimentação")
    public ResponseEntity<Void> patch(@PathVariable("id") Long id, @RequestBody Movimentacao movimentacao) {
        movimentacao.setId(id);
        boolean updated = service.updatePartial(movimentacao);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
