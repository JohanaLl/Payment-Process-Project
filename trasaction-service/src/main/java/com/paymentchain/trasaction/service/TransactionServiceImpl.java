package com.paymentchain.trasaction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.trasaction.entity.Transaction;
import com.paymentchain.trasaction.exception.TransactionNotFoundException;
import com.paymentchain.trasaction.repository.TransactionRepository;

@Service
public class TransactionServiceImpl extends CommonServiceImpl<Transaction, TransactionRepository> implements TransactionService {

	public TransactionServiceImpl(TransactionRepository repository) {
		super(repository);
	}
	
    @Override
    @Transactional
    public Transaction create(Transaction transaction) {
    
    	Optional.of(transaction)
    		.filter(trx -> trx.getFee() > 0 && trx.getAmount() > trx.getFee())
    		.ifPresent(trx -> trx.setAmount(trx.getAmount() - trx.getFee()));
    	
//    	transaction.setStatus(transaction.updateStatus(transaction));
    	
        return repository.save(transaction);
    }
    
	/**
	 * Buscar transacciones por numero de iban
	 */
    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findByIban(String iban) {
    	
    	List<Transaction> transactions = repository.findByIban(iban);
    	
//    	if (transactions.isEmpty())
//			throw new TransactionNotFoundException(iban);

    			
    	return transactions;
    }

}
