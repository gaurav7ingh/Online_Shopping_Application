package com.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.service.CustomerService;



@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cSer;

	@PostMapping(value = "/")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException {
		System.out.println( customer.getFirstName());
		System.out.println( customer.getLastName());
		System.out.println( customer.getEmail());
		
		
		Customer customer2 =  cSer.addCustomer(customer);
		
		return new ResponseEntity<Customer>(customer2,HttpStatus.OK);

	}

}
