package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.repository.CurrentUserSessionRepo;
import com.shopping.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping
	public ResponseEntity<Customer> registercustomerHandler(@RequestBody Customer cust) throws CustomerException{
		
		Customer savedcustomer =  customerService.addCustomer(cust);
		return new ResponseEntity<Customer>(savedcustomer,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Customer> updatedCustomerHandler(@RequestBody Customer cust,@RequestParam(required=false)String key)throws CustomerException{
		
		Customer updatedCustomer = customerService.updateCustomer(cust, key);
		
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomerHandler(String email)throws CustomerException{
		
		Customer deletecustomer = customerService.removeCustomer(email);
		return new ResponseEntity<Customer>(deletecustomer,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/{email}")
	public ResponseEntity<Customer> viewCustomerbyEmailHandler(@PathVariable("email") String email)throws CustomerException{
		
		Customer viewCustomer = customerService.viewCustomer(email);
		return new ResponseEntity<Customer>(viewCustomer,HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomerHandler()throws CustomerException{
		
		List<Customer> viewAllCustomer = customerService.ViewAllCustomers();
		return new ResponseEntity<List<Customer>>(viewAllCustomer,HttpStatus.OK);
	}
	
	
}
