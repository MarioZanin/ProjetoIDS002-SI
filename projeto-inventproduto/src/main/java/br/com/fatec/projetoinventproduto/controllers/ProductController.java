package br.com.fatec.projetoinventproduto.controllers;

import br.com.fatec.projetoinventproduto.model.Product;
import br.com.fatec.projetoinventproduto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET - Obter todos os produtos
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET - Obter produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // POST - Criar novo produto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // DELETE - Excluir produto por ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    // PUT - Substituir informações do produto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH - Atualizar parcialmente o produto
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            if (product.getName() != null) {
                existingProduct.setName(product.getName());
            }
            if (product.getDescription() != null) {
                existingProduct.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getQuantity() != null) {
                existingProduct.setQuantity(product.getQuantity());
            }
            if (product.getCategory() != null) {
                existingProduct.setCategory(product.getCategory());
            }
            productRepository.save(existingProduct);
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}












//@Controller
//@RequestMapping("/products") // Alterando o mapeamento da rota para "/products"
//public class ProductController {
//    private final ProductService productService;
//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//    @GetMapping
//    public String home(){
//        return "Products";
//    }
//  @GetMapping
    //public String showProductsPage(Model model) {
    //    List<Product> products = productService.getAllProducts();
    //    model.addAttribute("products", products); // Passando a lista de produtos para o modelo
    //    return "products"; // Retorna o nome da página HTML sem extensão
    //}
//
//    @GetMapping("/{id}")
//    @ResponseBody
//    public Product getProductById(@PathVariable Long id) {
//        return productService.getProductById(id).orElse(null); // Retorna o produto ou null se não encontrado
//    }
//    @PostMapping
//    @ResponseBody
//    public Product createProduct(@RequestBody Product product) {
//        return productService.createProduct(product);
//    }
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//    }
//    @PutMapping("/{id}")
//    @ResponseBody
//    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
//        return productService.updateProduct(id, product);
//    }
//}
