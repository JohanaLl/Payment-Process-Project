package com.paymentchain.trasaction.exception;

/*
 * Excepción base personalizada
 */

public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(String iban) {
	     super("No se encontró el transacción con iban: " + iban);
	}
}