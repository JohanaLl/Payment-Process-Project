package com.paymentchain.billing.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.billing.dto.BillingRequest;
import com.paymentchain.billing.entities.Billing;

@Mapper(componentModel = "spring")
public interface BillingRequestMapper {

	// Convierte un BillingRequest (DTO de entrada) a una entidad Billing
	@Mappings({
        @Mapping(source = "customer", target = "customerId")})
	Billing BillingRequestToBilling(BillingRequest source);
	
	//Convierte una lista de BillingRequest a una lista de Billing
	List<Billing> InvoiceRequestListToInvoiceList(List<BillingRequest> source);
	
	//Convierte una entidad Billing a un BillingRequest (mapping inverso)
	@InheritInverseConfiguration
	BillingRequest InvoiceToInvoiceRequest(Billing source);  
    
	//Convierte una lista de Billing a una lista de BillingRequest
    @InheritInverseConfiguration
    List<BillingRequest> InvoiceListToInvoiceRequestList(List<Billing> source);
}
