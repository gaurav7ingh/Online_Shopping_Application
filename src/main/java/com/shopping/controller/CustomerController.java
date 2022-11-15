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
import com.shopping.model.CurrentUserSession;
import com.shopping.model.Customer;
import com.shopping.service.CustomerService;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService cSer;

	private LogInService logService;

	private CurrentUserSession cs;

	@PostMapping
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer)
			throws CustomerException, UserException {
		customer.setRole("customer");
		Customer savedcustomer = cSer.addCustomer(customer);
		return new ResponseEntity<>(savedcustomer, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid)
			throws LoginException, CustomerException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");
		Customer deletedCustomer = cSer.removeCustomer(customer);

		return new ResponseEntity<>(deletedCustomer, HttpStatus.ACCEPTED);
	}

	@PutMapping
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @RequestParam String uuid)
			throws CustomerException, LoginException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");
		Customer updatedCustomer;
		if (cs.getUserId() == customer.getCustomerId())
			updatedCustomer = cSer.updateCustomer(customer);
		else
			throw new CustomerException("You are not allowed to update the user Id...!");

		return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{location}")
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@RequestParam String uuid,
			@PathVariable("location") String location) throws CustomerException, LoginException, UserException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		List<Customer> customerlist = cSer.ViewAllCustomers(location);

		return new ResponseEntity<>(customerlist, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<Customer> viewCustomerHandler(@RequestParam String uuid)
			throws LoginException, CustomerException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Integer customerId = cs.getUserId();

		Customer viewCustomer = cSer.viewCustomer(customerId);

		return new ResponseEntity<>(viewCustomer, HttpStatus.OK);
	}

	@Autowired
	public void setcSer(CustomerService cSer) {
		this.cSer = cSer;
	}

	@Autowired
	public void setLogService(LogInService logService) {
		this.logService = logService;
	}

	public void setCs(CurrentUserSession cs) {
		this.cs = cs;
	}

}
