package com.paymentchain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "ProductResquest", description = "Model represent a product on a database")
@Data
public class ProductRequest {

	@Schema(description = "Product code", example = "PROD001")
	private String code;
	
	@Schema(description = "Product name", example = "Credit Card")
	private String name;
	
	@Schema(description = "Detail of product", example = "Premium credit card with rewards")
	private String detail;
}
