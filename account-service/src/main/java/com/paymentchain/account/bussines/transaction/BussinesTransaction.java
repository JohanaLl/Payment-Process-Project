package com.paymentchain.account.bussines.transaction;

import com.paymentchain.account.entities.Account;
import com.paymentchain.account.service.AccountService;
import com.paymentchain.common.controller.CommonController;


public class BussinesTransaction extends CommonController<Account, AccountService>{

	public BussinesTransaction(AccountService service) {
		super(service);
	}
	
	
	
}
