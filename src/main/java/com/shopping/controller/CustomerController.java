package com.shopping.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.shopping.exception.LoginException;
import com.shopping.exception.UserException;
import com.shopping.model.Customer;
import com.shopping.service.CustomerService;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService cSer;

	@Autowired
	private LogInService logService;

	@PostMapping
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer)
			throws CustomerException, UserException {
		Customer savedcustomer = cSer.addCustomer(customer);
		return new ResponseEntity<Customer>(savedcustomer, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid)
			throws CustomerException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Customer updatedCustomer = cSer.updateCustomer(customer);
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid)
			throws LoginException, CustomerException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Customer deletedCustomer = cSer.removeCustomer(customer);

		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable Integer customerId, @RequestParam String uuid)
			throws LoginException, CustomerException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Customer viewCustomer = cSer.viewCustomer(customerId);

		return new ResponseEntity<Customer>(viewCustomer, HttpStatus.OK);
	}

	@GetMapping("/locations/{location}")
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@RequestParam String uuid,@PathVariable("location") String location)
			throws CustomerException, LoginException, UserException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");
		
		if(!logService.adminOrNot(uuid))
			throw new UserException("You are not allowed to see all the customer...!");

		List<Customer> customerlist = cSer.ViewAllCustomers(location);

		return new ResponseEntity<List<Customer>>(customerlist, HttpStatus.OK);

	}
}
