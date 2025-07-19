package com.paymentchain.account.service;

import org.springframework.stereotype.Service;

import com.paymentchain.account.entities.Account;
import com.paymentchain.account.repository.AccountRepository;
import com.paymentchain.common.services.CommonServiceImpl;

@Service
public class AccountServiceImpl extends CommonServiceImpl<Account, AccountRepository> implements AccountService{

	public AccountServiceImpl(AccountRepository repository) {
		super(repository);
	}


}
