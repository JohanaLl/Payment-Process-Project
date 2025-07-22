package com.paymentchain.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.account.entities.Account;
import com.paymentchain.account.service.AccountService;
import com.paymentchain.common.controller.CommonController;

@RestController
@RequestMapping("/account")
public class AccountController extends CommonController<Account, AccountService>
{

	public AccountController(AccountService service) {
		super(service);
	}


	/**
	 * Método para buscar las cuentas de un cliente por cliente id
	 * @param custId
	 * @return
	 */
	@GetMapping("/customer/accounts")
	public List<Account> findByCustId(@RequestParam String custId) {
		List<Account> accounts = service.findByCustId(custId);
		return accounts;
	}
	
	@GetMapping("byIban/{iban}")
	public ResponseEntity<Account> findByIban(@PathVariable String iban) {
		Account account = service.findByIban(iban);
		if (account == null) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(account);
	}
	
}
