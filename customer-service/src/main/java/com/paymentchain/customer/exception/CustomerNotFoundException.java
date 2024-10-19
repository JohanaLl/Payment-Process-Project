package com.paymentchain.customer.exception;

/*
 * Excepción base personalizada
 */

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String code) {
	     super("No se encontró el cliente con código: " + code);
	}
}