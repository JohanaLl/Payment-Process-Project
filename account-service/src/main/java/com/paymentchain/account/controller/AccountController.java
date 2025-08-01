package com.paymentchain.account.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	/**
	 * Método para buscar cuentas por iban
	 * @param iban
	 * @return
	 */
	@GetMapping("/byIban/{iban}")
	public ResponseEntity<Optional<Account>> findByIban(@PathVariable String iban) {
		Optional<Account> account = service.findByIban(iban);
		
		if (!account.isPresent()) 
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("/modifyBanlance/{accountIban}/{amount}")
	public ResponseEntity<?> modifyBalance(@PathVariable("accountIban") String accountIban, @PathVariable("amount") double amount) {
		Optional<Account> account = service.findByIban(accountIban);
		System.out.println("iban " + accountIban);
		System.out.println("amount " + amount);
		
		if (!account.isPresent()) 
			return ResponseEntity.notFound().build();
		
		Account accountDB = account.get();
		accountDB.setBalance(accountDB.getBalance()  + amount);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(accountDB));
	}
	
}
