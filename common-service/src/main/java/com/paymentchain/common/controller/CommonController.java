package com.paymentchain.common.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paymentchain.common.services.CommonService;

public class CommonController<E, S extends CommonService<E>>  {

	//Inyección de dependencias por constructor
	protected S service;
	
	public CommonController(S service) {
		this.service = service;
	}

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<E> userOp = service.findById(id);

        if (!userOp.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userOp.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody E entity) {
        E entityDB = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
