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
	public List<Account> findByCustId(Long custId);
	
}
