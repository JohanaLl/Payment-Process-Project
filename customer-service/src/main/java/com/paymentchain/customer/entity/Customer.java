package com.paymentchain.customer.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Unique identifier of the customer", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	
	@Schema(description = "Customer code", example = "CUST001", required = true)
	private String code;
	
	@Schema(description = "International Bank Account Number", example = "ES9121000418450200051332")
	private String iban;
	
	@Schema(description = "Customer first name", example = "John", required = true)
	private String name;
	
	@Schema(description = "Customer last name", example = "Doe", required = true)
	private String surname;
	
	@Schema(description = "Customer phone number", example = "+1234567890")
	private String phone;
	
	@Schema(description = "Customer address", example = "123 Main St, City, Country")
	private String adress;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@Schema(description = "List of products associated with the customer")
	private List<CustomerProduct> products;
	
	@Transient
	@Schema(description = "Customer transactions", accessMode = Schema.AccessMode.READ_ONLY)
	private List<?> transaccions;
	
	@Transient
	@Schema(description = "Customer accounts", accessMode = Schema.AccessMode.READ_ONLY)
	private List<?> accounts;
}
