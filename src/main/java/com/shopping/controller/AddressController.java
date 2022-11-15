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
import com.shopping.model.CurrentUserSession;
import com.shopping.service.AddressService;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private AddressService aSer;

	private LogInService logService;

	private CurrentUserSession cs;

	@PostMapping
	public ResponseEntity<Address> addAddressHandler(@Valid @RequestBody Address a, @RequestParam String uuid)
			throws LoginException, AddressException, CustomerException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Integer customerId = cs.getUserId();

		Address add = aSer.createAddress(a, customerId);

		return new ResponseEntity<>(add, HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<Address> removeAddressHandler(@RequestBody Address add, @RequestParam String uuid)
			throws LoginException, AddressException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.removeAddress(add), HttpStatus.ACCEPTED);
	}

	@PutMapping
	public ResponseEntity<Address> updatedAddressHandler(@RequestBody Address add, @RequestParam String uuid)
			throws LoginException, AddressException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.updateAddress(add), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{addressId}")
	public ResponseEntity<Address> viewAddressHandler(@PathVariable Integer addressId, @RequestParam String uuid)
			throws LoginException, AddressException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		return new ResponseEntity<>(aSer.viewAddress(addressId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Set<Address>> viewAllAddressHandler(@RequestParam String uuid)
			throws LoginException, CustomerException, AddressException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Integer id = cs.getUserId();

		return new ResponseEntity<>(aSer.viewAllAddress(id), HttpStatus.OK);
	}

	@Autowired
	public void setaSer(AddressService aSer) {
		this.aSer = aSer;
	}

	@Autowired
	public void setLogService(LogInService logService) {
		this.logService = logService;
	}

	public void setCs(CurrentUserSession cs) {
		this.cs = cs;
	}

}
