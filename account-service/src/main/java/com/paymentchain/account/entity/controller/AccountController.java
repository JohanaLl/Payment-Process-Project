package com.paymentchain.account.entity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.account.entity.Account;
import com.paymentchain.common.controller.CommonController;

@RestController
@RequestMapping("/api/account/V1")
public class AccountController 
//	extends CommonController<Account, AccountService>
{

//	public AccountController(AccountService service) {
//		super(service);
//	}
	
	public String greet() {
		return "Hello from account";
	}
}
