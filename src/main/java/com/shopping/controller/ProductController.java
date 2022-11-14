package com.shopping.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.LoginException;
import com.shopping.exception.ProductException;
import com.shopping.exception.UserException;
import com.shopping.model.Product;
import com.shopping.service.LogInService;
import com.shopping.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private LogInService logService;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> viewAllProductsHandler(@RequestParam String uuid)
			throws ProductException, UserException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Product> products = productService.viewAllProducts();

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/categorys/{category}")
	public ResponseEntity<List<Product>> viewProductByCategoryHandler(@PathParam(value = "category") String category,
			@RequestParam String uuid) throws LoginException, ProductException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Product> products = productService.viewProductByCategory(category);

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/names/{productName}")
	public ResponseEntity<List<Product>> viewProductByNameHandler(@PathParam(value = "productName") String productName,
			@RequestParam String uuid) throws LoginException, ProductException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Product> products = productService.viewProductByProductName(productName);

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> viewProductHandler(@PathParam(value = "productId") Integer productId,
			@RequestParam String uuid) throws LoginException, ProductException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Product p = productService.viewProduct(productId);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}
