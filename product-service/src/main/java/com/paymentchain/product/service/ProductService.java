package com.paymentchain.product.service;

import com.paymentchain.common.services.CommonService;
import com.paymentchain.product.dto.ProductRequest;
import com.paymentchain.product.dto.ProductResponse;
import com.paymentchain.product.entity.Product;
import com.paymentchain.product.exception.BussinesRuleException;

public interface ProductService extends CommonService<Product>{

	public ProductResponse updateProduct(Long id, ProductRequest request) throws BussinesRuleException;
}
