package com.paymentchain.product.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.product.entity.Product;
import com.paymentchain.product.service.ProductService;

@RestController
@RequestMapping("/api/product/V1")
public class ProductController extends CommonController<Product, ProductService>{

	public ProductController(ProductService service) {
		super(service);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Product customer, @PathVariable Long id) {
		Optional<Product> productOp = service.findById(id);
		
		if (!productOp.isPresent()) 
			return ResponseEntity.notFound().build();
		
		Product productDB = productOp.get();
		productDB.setCode(customer.getCode());
		productDB.setName(customer.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productDB));
	}

}
