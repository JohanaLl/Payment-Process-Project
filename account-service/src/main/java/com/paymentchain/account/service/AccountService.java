package com.paymentchain.account.service;

import java.util.List;

import com.paymentchain.account.entities.Account;
import com.paymentchain.common.services.CommonService;

public interface AccountService extends CommonService<Account> {

	/**
	 * Método para buscar cuentas por id cliente
	 * @param custId
	 * @return
	 */
	public List<Account> findByCustId(String custId);
	
	
	/**
	 * Método para buscar cuentas por iban
	 * @param custId
	 * @return
	 */
	public Account findByIban(String iban);
}
