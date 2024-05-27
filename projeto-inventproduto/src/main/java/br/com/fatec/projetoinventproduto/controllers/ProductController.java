package br.com.fatec.projetoinventproduto.controllers;

import br.com.fatec.projetoinventproduto.model.Product;
import br.com.fatec.projetoinventproduto.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProductController implements IController<Product> {

    @Autowired
    private ProductService service;

    @Override
    @GetMapping(produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Retorna a lista de produtos")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> get(@PathVariable("id") Long id) {
        Optional<Product> produto = service.getProductById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    @Operation(summary = "Cria um produto")
    public ResponseEntity<Product> post(@RequestBody Product product) {
        Product createdProduct = service.createProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Boolean> put(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = service.getProductById(id);
        if (existingProduct.isPresent()) {
            service.updateProduct(id, product);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um produto")
    public ResponseEntity<Product> patch(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = service.getProductById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = service.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Exclui um produto")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (service.getProductById(id).isPresent()) {
            service.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
