package com.paymentchain.trasaction.service;

import org.springframework.stereotype.Service;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.trasaction.entity.Transaction;
import com.paymentchain.trasaction.repository.TransactionRepository;

@Service
public class TransactionServiceImpl extends CommonServiceImpl<Transaction, TransactionRepository> implements TransactionService {

	public TransactionServiceImpl(TransactionRepository repository) {
		super(repository);
	}

}
