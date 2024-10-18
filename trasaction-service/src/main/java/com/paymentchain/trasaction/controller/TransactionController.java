package com.paymentchain.trasaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.trasaction.entity.Transaction;
import com.paymentchain.trasaction.service.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends CommonController<Transaction, TransactionService>{

	public TransactionController(TransactionService service) {
		super(service);
	}
	
	/**
	 * Método para crear transacciones
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Transaction transaction) {
		Transaction trxDB = service.create(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(trxDB);
	}
}
