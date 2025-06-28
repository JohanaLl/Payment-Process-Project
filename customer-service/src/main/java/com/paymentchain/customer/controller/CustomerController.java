package com.paymentchain.customer.controller;

import java.net.UnknownHostException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.common.controller.CommonController;
import com.paymentchain.customer.bussines.transaction.BussinesTransaction;
import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.exception.BussinesRuleException;
import com.paymentchain.customer.service.CustormerService;

@RestController
@RequestMapping("/api/customer/V1")
public class CustomerController extends CommonController<Customer, CustormerService>{

	@Autowired
	BussinesTransaction bt;
	
	public CustomerController(CustormerService service) { //WebClient.Builder webClientBuilder
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
	
	/**
	 * Metodo para crear clientes y asignarle los products que llegan en la creación
	 * @throws BussinesRuleException 
	 * @throws UnknownHostException 
	 */
	@PostMapping("/postCreate")
	public ResponseEntity<?> postCreate(@RequestBody Customer customer) throws BussinesRuleException, UnknownHostException {
		Customer cust = bt.postCreate(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(cust);
	}
	
	
	/**
	 * Metodo que devuelve un cliente por su código y asigna el nombre de cada producto del cliente
	 * @param code
	 * @return
	 */
	@GetMapping("/full")
	public Customer getByCode(@RequestParam String code) {
		
		Customer customer = bt.getByCode(code);
		return customer;
	}

}
