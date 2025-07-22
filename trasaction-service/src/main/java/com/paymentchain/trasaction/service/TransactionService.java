package com.paymentchain.trasaction.service;

import java.util.List;

import com.paymentchain.common.services.CommonService;
import com.paymentchain.trasaction.entity.Transaction;

public interface TransactionService extends CommonService<Transaction>{

	public Transaction create(Transaction transaction);
	
	/**
	 * Buscar transacciones por numero de iban
	 */
	public List<Transaction> findByIban(String iban);
	
	/**
	 * Buscar transacciones por numero de referencia
	 */
	public Transaction findByReference(String reference);
	
}
