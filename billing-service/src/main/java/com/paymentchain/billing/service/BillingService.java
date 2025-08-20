package com.paymentchain.billing.service;

import com.paymentchain.billing.dto.BillingRequest;
import com.paymentchain.billing.dto.BillingResponse;
import com.paymentchain.billing.entities.Billing;
import com.paymentchain.common.services.CommonService;

public interface BillingService extends CommonService<Billing> {

	public BillingResponse updateBilling(Long id, BillingRequest request);
}
