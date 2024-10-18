package com.paymentchain.trasaction.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.trasaction.entity.Transaction;
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
    		.filter(trx -> trx.getFee() > 0)
    		.ifPresent(trx -> trx.setAmount(trx.getAmount() - trx.getFee()));
    	
    	transaction.setStatus(transaction.updateStatus(transaction));
    	
        return repository.save(transaction);
    }

}
