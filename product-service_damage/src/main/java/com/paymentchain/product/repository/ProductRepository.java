package com.paymentchain.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymentchain.product.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
