package com.paymentchain.billing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.billing.entities.Billing;
import com.paymentchain.billing.service.BillingService;
import com.paymentchain.common.controller.CommonController;
@RestController
@RequestMapping("/billing")
public class BillingController extends CommonController<Billing, BillingService> {

	public BillingController(BillingService service) {
		super(service);
	}

}
