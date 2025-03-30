package com.paymentchain.trasaction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * Método para buscar las transacciones por iban
	 */
	@GetMapping("/customer/transactions")
	public List<Transaction> findByIban(@RequestParam String iban) {
		
		List<Transaction> transactions = service.findByIban(iban);
		return transactions;
	}
	
	/**
	 * Método para editar una transacción
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Transaction transaction, @PathVariable Long id) {
		Optional<Transaction> transactionOp = service.findById(id);
		
		if (!transactionOp.isPresent()) 
			return ResponseEntity.notFound().build();
		
		Transaction transactionDB = transactionOp.get();
		transactionDB.setDate(transaction.getDate());
		transactionDB.setAmount(transaction.getAmount());
		transactionDB.setFee(transaction.getFee());
		transactionDB.setDescription(transaction.getDescription());
		transactionDB.setStatus(transaction.getStatus());
		transactionDB.setChanel(transaction.getChanel());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transactionDB));
	}
}
