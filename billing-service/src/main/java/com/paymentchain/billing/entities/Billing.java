package com.paymentchain.billing.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(name = "Billing", description = "Model represent a invoice on a database")
public class Billing {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   @Schema(description = "ID único de billing", example = "1")
   private long id;
   @Schema(name = "customerId", required = true, example = "2", description = "Unique Id of customer that represent the owner of billing")
   private long customerId;
   @Schema(name = "number", required = true, example = "3", description = "Number guiven of fisical billing")
   private String number;
   @Schema(name = "detail", required = true, example = "DETAIL", description = "Detail of billing")
   private String detail;
   @Schema(name = "amount", required = true, example = "100", description = "Amount of billing")
   private double amount;  
}
