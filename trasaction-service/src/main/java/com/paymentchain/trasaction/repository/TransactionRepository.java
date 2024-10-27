package com.paymentchain.trasaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paymentchain.trasaction.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	/**
	 * Buscar transacciones por numero de iban
	 */
	@Query(value = "SELECT t FROM Transaction t WHERE t.accountIban = ?1")
	public List<Transaction> findByIban(String iban);
}
