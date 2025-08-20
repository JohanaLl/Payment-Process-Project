package com.paymentchain.billing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentchain.billing.common.BillingRequestMapper;
import com.paymentchain.billing.common.BillingResponseMapper;
import com.paymentchain.billing.dto.BillingRequest;
import com.paymentchain.billing.dto.BillingResponse;
import com.paymentchain.billing.entities.Billing;
import com.paymentchain.billing.repository.BillingRepository;
import com.paymentchain.common.services.CommonServiceImpl;

@Service
public class BillingServiceImpl extends CommonServiceImpl<Billing, BillingRepository> implements BillingService {

	@Autowired
	BillingRequestMapper requestMapper;
	@Autowired
	BillingResponseMapper responseMapper;
	
	public BillingServiceImpl(BillingRepository repository) {
		super(repository);
	}
	
	// Método para actualizar billing
    public BillingResponse updateBilling(Long id, BillingRequest request) {
        Optional<Billing> existingBilling = findById(id);
        
        if (!existingBilling.isPresent()) {
            throw new RuntimeException("Billing not found with id: " + id);
        }
        
        Billing billingToUpdate = existingBilling.get();
        // Actualizar solo los campos que vienen en el request
        billingToUpdate.setDetail(request.getDetail());
        billingToUpdate.setAmount(request.getAmount());
        
        Billing updatedBilling = save(billingToUpdate);
        return responseMapper.BillingToBillingRespose(updatedBilling);
    }

}
