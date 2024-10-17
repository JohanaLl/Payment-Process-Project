package com.paymentchain.customer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paymentchain.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>  {

	//?1 esto indica que es un parámetro
	
	@Query(value = "SELECT c FROM Customer c WHERE c.code = ?1")
	public Customer findByCode(String code);
	
	@Query(value = "SELECT c FROM Customer c WHERE c.iban = ?1")
	public Customer findByAccount(String iban);
}
