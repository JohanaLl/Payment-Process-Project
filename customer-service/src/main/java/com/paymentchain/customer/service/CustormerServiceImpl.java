package com.paymentchain.customer.service;

import org.springframework.stereotype.Service;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.repository.CustomerRepository;

@Service
public class CustormerServiceImpl extends CommonServiceImpl<Customer, CustomerRepository> implements CustormerService{

	public CustormerServiceImpl(CustomerRepository repository) {
		super(repository);
	}
	
	public Customer findByCode(String code) {
		return repository.findByCode(code);
	}
	
	public Customer findByAccount(String iban) {
		return repository.findByAccount(iban);
	}

}
