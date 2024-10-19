package com.paymentchain.customer.service;

import org.springframework.stereotype.Service;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.exception.CustomerNotFoundException;
import com.paymentchain.customer.repository.CustomerRepository;

@Service
public class CustormerServiceImpl extends CommonServiceImpl<Customer, CustomerRepository> implements CustormerService{

	public CustormerServiceImpl(CustomerRepository repository) {
		super(repository);
	}
	
	public Customer findByCode(String code) {
		Customer customer = repository.findByCode(code);
		
		if (customer == null) {
			throw new CustomerNotFoundException(code);
		}
		return customer;
	}
	
	public Customer findByAccount(String iban) {
		return repository.findByAccount(iban);
	}

}
