package com.paymentchain.trasaction.service;

import java.util.List;
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
        return repository.save(transaction);
    }
    
	/**
	 * Buscar transacciones por numero de iban
	 */
    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findByIban(String iban) {
    	List<Transaction> transactions = repository.findByIban(iban);	
    	return transactions;
    }

    /**
	 * Buscar transacciones por numero de referencia
	 */
	@Override
	public Transaction findByReference(String reference) {
		Transaction transaction = repository.findByReference(reference);
		return transaction;
	}

}
