package br.com.fatec.projetoinventproduto.controllers;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> get(Long id);
    ResponseEntity<T> post(T obj);
    ResponseEntity<Boolean> put(Long id, T obj);
    ResponseEntity<T> patch(Long id, T obj);
    ResponseEntity<Void> delete(Long id);
}
