package com.paymentchain.product.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.product.entity.Product;
import com.paymentchain.product.exception.BussinesRuleException;
import com.paymentchain.product.service.ProductService;
import com.paymentchain.product.utils.UtilString;

@RestController
@RequestMapping("/product")
public class ProductController extends CommonController<Product, ProductService>{

	public ProductController(ProductService service) {
		super(service);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Product product, @PathVariable Long id) throws BussinesRuleException {
		
		Optional<Product> productOp = service.findById(id);
		if (!productOp.isPresent()) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.product.not.exists", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		
		Product productDB = productOp.get();
		productDB.setCode(product.getCode());
		productDB.setName(product.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productDB));
	}
	
	@PostMapping("/valid")
    public ResponseEntity<?> createWithValid(@RequestBody Product product) throws BussinesRuleException {
		if (UtilString.findIsNull(product.getCode()) || UtilString.findIsNull(product.getName())) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.product.cod.nom.isempty", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		
        Product productDB = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDB);
    }

}
