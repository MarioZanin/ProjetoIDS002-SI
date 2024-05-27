package br.com.fatec.projetoinventproduto.service;

import br.com.fatec.projetoinventproduto.model.Product;
import br.com.fatec.projetoinventproduto.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }
}
