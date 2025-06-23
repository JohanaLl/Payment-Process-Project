package com.paymentchain.billing.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymentchain.billing.entities.Billing;

public interface BillingRepository extends CrudRepository<Billing, Long>{

}
