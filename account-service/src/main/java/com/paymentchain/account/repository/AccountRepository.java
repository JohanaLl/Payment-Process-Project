package com.paymentchain.account.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paymentchain.account.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

	/**
	 * Método para buscar cuentas por id cliente
	 * @param custId
	 * @return
	 */
	@Query(value = "SELECT a FROM Account a WHERE a.customerId = ?1")
	public List<Account> findByCustId(Long custId);
}
