package com.paymentchain.trasaction.controller;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.trasaction.entity.Transaction;
import com.paymentchain.trasaction.service.TransactionService;

public class TransactionController extends CommonController<Transaction, TransactionService>{

	public TransactionController(TransactionService service) {
		super(service);
	}

}
