package com.paymentchain.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.product.common.ProductRequestMapper;
import com.paymentchain.product.common.ProductResponseMapper;
import com.paymentchain.product.dto.ProductRequest;
import com.paymentchain.product.dto.ProductResponse;
import com.paymentchain.product.entity.Product;
import com.paymentchain.product.exception.BussinesRuleException;
import com.paymentchain.product.repository.ProductRepository;

@Service
public class ProductServiceImpl extends CommonServiceImpl<Product, ProductRepository> implements ProductService {


	@Autowired
	ProductRequestMapper requestMapper;
	@Autowired
	ProductResponseMapper responseMapper;
	
	public ProductServiceImpl(ProductRepository repository) {
		super(repository);
	}

	/**
	 * Método para actualizar producto
	 */
	public ProductResponse updateProduct(Long id, ProductRequest request) throws BussinesRuleException {
		
		Optional<Product> productOp = findById(id);
		
		if (!productOp.isPresent()) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.product.not.exists", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		
		Product productDB = productOp.get();
		productDB.setCode(request.getCode());
		productDB.setName(request.getName());
		
		Product updatedProduct = save(productDB);
		return responseMapper.ProductToProductResponse(updatedProduct);
	}
}
