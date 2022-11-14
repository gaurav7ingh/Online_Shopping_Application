package com.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.LoginException;
import com.shopping.exception.ProductException;
import com.shopping.exception.UserException;
import com.shopping.model.Product;
import com.shopping.service.AdminService;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private LogInService logService;

	@PostMapping
	public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product, @RequestParam String uuid)
			throws ProductException, LoginException, UserException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		if (!logService.adminOrNot(uuid))
			throw new UserException("You are not allowed to add the product...!");

		Product p = adminService.addProduct(product);

		return new ResponseEntity<>(p, HttpStatus.CREATED);

	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Product> removeProductHandler(@PathVariable("productId") Integer productId,
			@RequestParam String uuid) throws ProductException, LoginException, UserException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		if (!logService.adminOrNot(uuid))
			throw new UserException("You are not allowed to Remove the product...!");

		Product p = adminService.removeProduct(productId);

		return new ResponseEntity<>(p, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product, @RequestParam String uuid)
			throws ProductException, LoginException, UserException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		if (!logService.adminOrNot(uuid))
			throw new UserException("You are not allowed to update the product...!");

		Product p = adminService.updateProduct(product);

		return new ResponseEntity<>(p, HttpStatus.CREATED);

	}

}
