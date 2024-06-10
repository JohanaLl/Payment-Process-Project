package com.paymentchain.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymentchain.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>  {

}
