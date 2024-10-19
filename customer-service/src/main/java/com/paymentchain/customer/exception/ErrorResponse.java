package com.paymentchain.customer.exception;

import lombok.Data;

/**
 * Representar la respuesta de error
 */

@Data
public class ErrorResponse {
	
	private String message;
    private String details;
}
