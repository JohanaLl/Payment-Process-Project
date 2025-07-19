package com.paymentchain.trasaction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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
import com.paymentchain.trasaction.exception.BussinesRuleException;
import com.paymentchain.trasaction.service.TransactionService;
import com.paymentchain.trasaction.utils.UtilString;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends CommonController<Transaction, TransactionService>{

	public TransactionController(TransactionService service) {
		super(service);
	}
	
	/**
	 * Método para crear transacciones
	 * @throws BussinesRuleException 
	 */
	@PostMapping("/valid")
	public ResponseEntity<?> createWithValid(@RequestBody Transaction trx) throws BussinesRuleException {
		if (!UtilString.findIsNull(trx.getId().toString()) || !UtilString.findIsNull(trx.getReference())) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.transaction.id.ref.isempty", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		Transaction trxDB = service.create(trx);
		return ResponseEntity.status(HttpStatus.CREATED).body(trxDB);
	}
	
	/**
	 * Método para buscar las transacciones por iban
	 * @throws BussinesRuleException 
	 */
	@GetMapping("/customer/transactions")
	public List<Transaction> findByIban(@RequestParam String iban) throws BussinesRuleException {
		List<Transaction> transactions = service.findByIban(iban);
		
		if (transactions.isEmpty()) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.transaction.not.exists", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		return transactions;
	}
	
	/**
	 * Método para editar una transacción
	 * @throws BussinesRuleException 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Transaction transaction, @PathVariable Long id) throws BussinesRuleException {
		Optional<Transaction> transactionOp = service.findById(id);
		
		if (!transactionOp.isPresent()) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("error.validation.transaction.not.exists", HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}
		
		Transaction transactionDB = transactionOp.get();
		transactionDB.setDate(transaction.getDate());
		transactionDB.setAmount(transaction.getAmount());
		transactionDB.setFee(transaction.getFee());
		transactionDB.setDescription(transaction.getDescription());
		transactionDB.setStatus(transaction.getStatus());
		transactionDB.setChanel(transaction.getChanel());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transactionDB));
	}
	
	public Double validarSaldo(Transaction trx) {
		
		double saldo = 0;
		
		if (trx.getAmount() > 0) {
			saldo += trx.getAmount();
		} else {
			saldo -= trx.getAmount();
		}
		
		return saldo;
	}

}
