package com.paymentchain.trasaction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.trasaction.bussines.transaction.BussinesTransaction;
import com.paymentchain.trasaction.entity.Transaction;
import com.paymentchain.trasaction.exception.BussinesRuleException;
import com.paymentchain.trasaction.service.TransactionService;
import com.paymentchain.trasaction.utils.UtilString;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transaction API", description = "This API serve all funtionality for management transactions")
@RestController
@RequestMapping("/transaction")
public class TransactionController extends CommonController<Transaction, TransactionService>{

	@Autowired
	BussinesTransaction bt;
	
	public TransactionController(TransactionService service) {
		super(service);
	}
	
	/**
	 * Método para crear transacciones
	 * @throws BussinesRuleException 
	 */
	@PostMapping("/createTRX")
	public ResponseEntity<?> createTRX(@RequestBody Transaction trx) throws BussinesRuleException {
		Transaction trxDB = bt.createTrx(trx);
		return ResponseEntity.status(HttpStatus.CREATED).body(trxDB);
	}
	
	/**
	 * Método para buscar las transacciones de un cliente por iban
	 * @throws BussinesRuleException 
	 */
	@Operation(description = "Returns transaction found by Iban", summary = "Return 204 if no data found")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Exito"),
			@ApiResponse(responseCode = "500", description = "Internal error")})
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
	 * Método para buscar transacciones por numero de referencia
	 * @param reference
	 * @return
	 * @throws BussinesRuleException
	 */
	@GetMapping("byReference/{reference}")
	public Transaction findByReference(@PathVariable String reference) throws BussinesRuleException {
		Transaction transaction = service.findByReference(reference);
		return transaction;
	}
	
	/**
	 * Método para editar una transacción
	 * @throws BussinesRuleException 
	 */
	@Operation(description = "Returns an edited transaction into Response", summary = "Return 204 if no data found")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Exito"),
			@ApiResponse(responseCode = "500", description = "Internal error")})
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
	
}
