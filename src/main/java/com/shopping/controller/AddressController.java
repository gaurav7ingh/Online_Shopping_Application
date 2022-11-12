package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.AddressException;
import com.shopping.exception.CustomerException;
import com.shopping.model.Address;
import com.shopping.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	private AddressService aSer;

	@PostMapping("/{customerId}")
	public ResponseEntity<Address> addAddressHandler(@RequestBody Address a,@PathVariable Integer customerId) throws AddressException, CustomerException {

		return new ResponseEntity<Address>(aSer.createAddress(a,customerId), HttpStatus.OK);
	}
}
