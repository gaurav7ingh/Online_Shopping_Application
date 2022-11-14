package com.shopping.controller;

import java.util.Set;

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

import com.shopping.exception.AddressException;
import com.shopping.exception.CustomerException;
import com.shopping.exception.LoginException;
import com.shopping.model.Address;
import com.shopping.service.AddressService;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	private AddressService aSer;

	@Autowired
	private LogInService logService;

	@PostMapping("/{customerId}")
	public ResponseEntity<Address> addAddressForCustomerHandler(@Valid @RequestBody Address a,
			@PathVariable Integer customerId, @RequestParam String uuid)
			throws AddressException, CustomerException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Address add = aSer.createAddress(a, customerId);

		return new ResponseEntity<>(add, HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<Address> removeAddressHandler(@RequestBody Address add, @RequestParam String uuid)
			throws AddressException, CustomerException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.removeAddress(add), HttpStatus.ACCEPTED);
	}

	@PutMapping
	public ResponseEntity<Address> updatedAddressHandler(@RequestBody Address add, @RequestParam String uuid)
			throws AddressException, CustomerException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.updateAddress(add), HttpStatus.ACCEPTED);
	}

	@GetMapping("/address/{addressId}")
	public ResponseEntity<Address> viewAddressHandler(@PathVariable Integer addressId, @RequestParam String uuid)
			throws AddressException, CustomerException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.viewAddress(addressId), HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Set<Address>> viewAllAddressOfCustomerHandler(@PathVariable(value = "customerId") Integer id,
			@RequestParam String uuid) throws AddressException, CustomerException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.viewAllAddress(id), HttpStatus.OK);
	}

}
