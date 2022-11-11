package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.service.CustomerService;

@RestController
//@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addcustHandler(@RequestBody Customer cust) throws CustomerException{
		
		Customer register =  cService.addCustomer(cust);
		return new ResponseEntity<Customer>(register,HttpStatus.OK);
		
	}
	
	
}
