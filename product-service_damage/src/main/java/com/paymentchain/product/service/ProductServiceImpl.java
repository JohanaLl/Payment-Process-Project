package com.paymentchain.product.service;

import org.springframework.stereotype.Service;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.product.entity.Product;
import com.paymentchain.product.repository.ProductRepository;

@Service
public class ProductServiceImpl extends CommonServiceImpl<Product, ProductRepository> implements ProductService {

	public ProductServiceImpl(ProductRepository repository) {
		super(repository);
	}

}
