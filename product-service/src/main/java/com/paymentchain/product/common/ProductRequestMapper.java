package com.paymentchain.product.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.paymentchain.product.dto.ProductRequest;
import com.paymentchain.product.entity.Product;


@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

	// Convierte un ProductRequest (DTO de entrada) a una entidad Product
	Product ProductRequestToProduct(ProductRequest source);
	
	//Convierte una lista de ProductRequest a una lista de Product
	List<Product> ProductRequestListToProductList(List<ProductRequest> source);
	
	//Convierte una entidad Product a un ProductRequest (mapping inverso)
	@InheritInverseConfiguration
	ProductRequest ProductToProductRequest(Product source);
		
	//Convierte una lista de Product a una lista de ProductRequest
	@InheritInverseConfiguration
	List<ProductRequest> ProductListToProductRequestList(List<Product> source);
}
