package com.paymentchain.trasaction.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymentchain.trasaction.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}
