package com.paymentchain.product.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	
	@Schema(description = "Product code", example = "PROD001", required = true)
	private String code;
	
	@Schema(description = "Product name", example = "Credit Card", required = true)
	private String name;
	
	@Schema(description = "Detail of product", example = "Premium credit card with rewards")
	private String detail;
}
