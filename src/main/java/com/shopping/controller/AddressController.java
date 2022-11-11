package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.AddressException;
import com.shopping.model.Address;
import com.shopping.service.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	private AddressService aSer;

	@PostMapping
	public ResponseEntity<Address> re(@RequestBody Address a) throws AddressException {

		return new ResponseEntity<Address>(aSer.createAddress(a), HttpStatus.OK);
	}

}
