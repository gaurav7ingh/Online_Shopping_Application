package com.shopping.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.AddressException;
import com.shopping.exception.CustomerException;
import com.shopping.model.Address;
import com.shopping.service.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/add")
public class AddressController {

	@Autowired
	private AddressService aSer;

	@PostMapping("/")
	public ResponseEntity<Address> saveAdd(@RequestBody Address add) throws AddressException, CustomerException {

		return new ResponseEntity<Address>(aSer.addAddress(add), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<Address> updatedAdd(@RequestBody Address add) throws AddressException, CustomerException {

		return new ResponseEntity<Address>(aSer.updateAddress(add), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Set<Address>> viewALlADd(@PathVariable(value = "id") Integer id)
			throws AddressException, CustomerException {

		return new ResponseEntity<Set<Address>>(aSer.viewAllAddress(id), HttpStatus.OK);
	}

	@PutMapping("/viewCheck")
	public ResponseEntity<Address> viewAdd(@RequestBody Address add) throws AddressException, CustomerException {

		return new ResponseEntity<Address>(aSer.viewAddress(add), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Address> removeAdd(@RequestBody Address add) throws AddressException, CustomerException {

		return new ResponseEntity<Address>(aSer.removeAddress(add), HttpStatus.OK);
	}

}
