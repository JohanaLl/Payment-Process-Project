package com.paymentchain.customer.service;

import com.paymentchain.common.services.CommonService;
import com.paymentchain.customer.entity.Customer;

public interface CustormerService extends CommonService<Customer> {

	public Customer findByCode(String code);
	
	public Customer findByAccount(String iban);
}
