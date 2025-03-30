package com.paymentchain.trasaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.trasaction.entities.Billing;
import com.paymentchain.trasaction.service.BillingService;

@RestController
@RequestMapping("/api/billing")
public class BillingController extends CommonController<Billing, BillingService> {

	public BillingController(BillingService service) {
		super(service);
	}

}
