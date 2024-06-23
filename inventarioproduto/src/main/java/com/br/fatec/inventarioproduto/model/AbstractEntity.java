package com.br.fatec.inventarioproduto.model;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }
}