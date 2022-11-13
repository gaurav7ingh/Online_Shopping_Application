package com.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.security.auth.login.LoginException;
import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.service.CustomerService;
import com.shopping.service.LogInService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cSer;

	@Autowired
	private LogInService logService;

	private String currentSession;

	public void setCurrentSession(String uuid) {
		this.currentSession = uuid;
	}

	@PostMapping("/")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException {
		Customer savedcustomer =  cSer.addCustomer(customer);
		return new ResponseEntity<Customer>(savedcustomer,HttpStatus.OK);
	}
	

	@PutMapping
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid) throws CustomerException, LoginException{
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		
		Customer updatedCustomer = cSer.updateCustomer(customer);
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid) throws LoginException, CustomerException{
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Customer deletedCustomer = cSer.removeCustomer(customer);
		
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getcustomers")
	public ResponseEntity<Customer> viewCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid) throws LoginException, CustomerException{
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Customer viewCustomer = cSer.viewCustomer(customer);
		
		return new ResponseEntity<Customer>(viewCustomer,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@RequestParam String uuid) throws CustomerException, LoginException{
		
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		
		
		List<Customer> customerlist = cSer.ViewAllCustomers();
		
		return new ResponseEntity<List<Customer>>(customerlist,HttpStatus.OK);
		
	}
}

