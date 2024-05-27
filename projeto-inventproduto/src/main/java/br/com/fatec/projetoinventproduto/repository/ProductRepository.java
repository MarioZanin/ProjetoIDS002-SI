package br.com.fatec.projetoinventproduto.repository;

import br.com.fatec.projetoinventproduto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
