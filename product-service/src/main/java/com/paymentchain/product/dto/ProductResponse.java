package com.paymentchain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "ProductResponse", description = "Model represent a product on a database")
@Data
public class ProductResponse {

	@Schema(description = "Unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long productId;
	
	@Schema(description = "Product code", example = "PROD001")
	private String code;
	
	@Schema(description = "Product name", example = "Credit Card")
	private String name;
	
	@Schema(description = "Detail of product", example = "Premium credit card with rewards")
	private String detail;
}
