package com.br.fatec.inventarioproduto.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> get(Long id);
    ResponseEntity<T> post(T t);
    ResponseEntity<Boolean> put(T t);
    ResponseEntity<T> patch(T t);
    ResponseEntity<Void> delete(Long id);
}
