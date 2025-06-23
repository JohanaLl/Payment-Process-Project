package com.paymentchain.billing.service;

import org.springframework.stereotype.Service;

import com.paymentchain.billing.entities.Billing;
import com.paymentchain.billing.repository.BillingRepository;
import com.paymentchain.common.services.CommonServiceImpl;

@Service
public class BillingServiceImpl extends CommonServiceImpl<Billing, BillingRepository> implements BillingService {

	public BillingServiceImpl(BillingRepository repository) {
		super(repository);
	}

}
