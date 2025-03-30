package com.paymentchain.trasaction.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Billing {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long customerId;
    private String number;
    private String detail;
    private double amount;  
}
