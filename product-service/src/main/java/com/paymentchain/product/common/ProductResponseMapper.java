package com.paymentchain.product.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.product.dto.ProductResponse;
import com.paymentchain.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

	//Convierte una entidad Product a un ProductResponse (DTO de salida)
	@Mappings({
		@Mapping(source = "id", target = "productId")
	})
	ProductResponse ProductToProductResponse(Product source);
		
	//Convierte una lista de Product a una lista de ProductResponse
	List<ProductResponse> ProductListToProductResponseList(List<Product> source);
	
	//Convierte un ProductResponse a una entidad Product (mapping inverso)
	@InheritInverseConfiguration
	Product ProductResponseToProduct(ProductResponse source);
	
	//Convierte una lista de ProductResponse a una lista de Product
	@InheritInverseConfiguration
	List<Product> ProductResponseListToProductList(List<ProductResponse> source);
}
