package com.paymentchain.customer.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.service.CustormerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends CommonController<Customer, CustormerService>{

	public CustomerController(CustormerService service) {
		super(service);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Customer customer, @PathVariable Long id) {
		Optional<Customer> customerOp = service.findById(id);
		
		if (!customerOp.isPresent()) 
			return ResponseEntity.notFound().build();
		
		Customer custormerDB = customerOp.get();
		custormerDB.setCode(customer.getCode());
		custormerDB.setIban(customer.getIban());
		custormerDB.setName(customer.getName());
		custormerDB.setSurname(customer.getSurname());
		custormerDB.setPhone(customer.getPhone());
		custormerDB.setAdress(customer.getAdress());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(custormerDB));
	}

}
