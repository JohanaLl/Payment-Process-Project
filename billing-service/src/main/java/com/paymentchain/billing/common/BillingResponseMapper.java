package com.paymentchain.billing.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.billing.dto.BillingResponse;
import com.paymentchain.billing.entities.Billing;

@Mapper(componentModel = "spring")
public interface BillingResponseMapper {

	//Convierte una entidad Billing a un BillingResponse (DTO de salida)
    @Mappings({
    	@Mapping(source = "customerId", target = "customer"),
    	@Mapping(source = "id", target = "billingId")})
	BillingResponse BillingToBillingRespose(Billing source);  
	
    //Convierte una lista de Billing a una lista de BillingResponse
	List<BillingResponse> BillingListToBillingResposeList(List<Billing> source);    
	
	//Convierte un BillingResponse a una entidad Billing (mapping inverso)
	@InheritInverseConfiguration
	Billing BillingResponseToBilling(BillingResponse source);
	
	//Convierte una lista de BillingResponse a una lista de Billing
	@InheritInverseConfiguration
	List<Billing> BillingResponseToBillingList(List<BillingResponse> source);    
 
}
