package com.paymentchain.billing.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(name = "BillingResponse", description = "Model represent a invoice on a database")
@Data
public class BillingResponse {

	   @Schema(description = "ID único de billing", example = "1")
	   private long billingId; 
	   @Schema(name = "customer", required = true, example = "2", description = "Unique Id of customer that represent the owner of billing")
	   private long customer;
	   @Schema(name = "number", required = true, example = "3", description = "Number guiven of fisical billing")
	   private String number;
	   @Schema(name = "detail", required = true, example = "DETAIL", description = "Detail of billing")
	   private String detail;
	   @Schema(name = "amount", required = true, example = "100", description = "Amount of billing")
	   private double amount;  
}
