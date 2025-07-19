package com.paymentchain.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
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


}
