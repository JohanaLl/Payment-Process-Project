package com.paymentchain.account.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymentchain.account.entities.Account;
import com.paymentchain.account.repository.AccountRepository;
import com.paymentchain.common.services.CommonServiceImpl;


@Service
public class AccountServiceImpl extends CommonServiceImpl<Account, AccountRepository> implements AccountService{

	public AccountServiceImpl(AccountRepository repository) {
		super(repository);
	}

	/**
	 * Método para buscar cuentas por id cliente
	 * @param custId
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Account> findByCustId(String custId) {
		
		List<Account> accounts = repository.findByCustId(custId);
		
		return accounts;
	}

	/**
	 * Método para buscar cuentas por iban
	 * @param custId
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Account findByIban(String iban) {
		
		Account account = repository.findByIban(iban);
		
		return account;
	}

	

}
