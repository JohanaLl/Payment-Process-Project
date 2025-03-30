package com.paymentchain.trasaction.service;

import org.springframework.stereotype.Service;

import com.paymentchain.common.services.CommonServiceImpl;
import com.paymentchain.trasaction.entities.Billing;
import com.paymentchain.trasaction.repository.BillingRepository;

@Service
public class BillingServiceImpl extends CommonServiceImpl<Billing, BillingRepository> {

	public BillingServiceImpl(BillingRepository repository) {
		super(repository);
	}

}
