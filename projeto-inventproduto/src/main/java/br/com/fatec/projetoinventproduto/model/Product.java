package br.com.fatec.projetoinventproduto.model;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_produto")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product extends AbstractEntity {
    private static final long serialVersionUID = 1L;
	@Column(name = "nm_nome", length = 60)
	private String name;
	@Column(name = "ds_descricao", length = 120)
	private String description;
    @Column(name = "pr_preco", nullable = false)
	private BigDecimal price;
	@Column(name = "qt_quantidade", nullable = false, length = 10)
	private Integer quantity;
	@Column(name = "ct_categoria", nullable = false, length = 100)
	private String category;
	
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    // Default constructor
    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, quantity, category);
    }
}
