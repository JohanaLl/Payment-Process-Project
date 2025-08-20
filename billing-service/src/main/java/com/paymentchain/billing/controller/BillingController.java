package com.paymentchain.billing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.billing.dto.BillingRequest;
import com.paymentchain.billing.dto.BillingResponse;
import com.paymentchain.billing.entities.Billing;
import com.paymentchain.billing.service.BillingService;
import com.paymentchain.common.controller.CommonController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Billing API", description = "This API serve all funtionality for management billing")
@RestController
@RequestMapping("/billing")
public class BillingController extends CommonController<Billing, BillingService> {
	
	public BillingController(BillingService service) {
		super(service);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BillingResponse> edit(@RequestBody BillingRequest billing, @PathVariable Long id) {
		
		try {
	        BillingResponse updatedBilling = service.updateBilling(id, billing);
	        return ResponseEntity.ok(updatedBilling);
	    } catch (RuntimeException e) {
	        return ResponseEntity.notFound().build();
	    }
	}

}
