package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.ProductException;
import com.shopping.exception.UserException;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.model.UserDTO;
import com.shopping.service.LogInService;
import com.shopping.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private LogInService logInService;
	
	private String currentSession;

	public void setCurrentSession(String uuid) {
		this.currentSession = uuid;
	}
	
	
	public ResponseEntity<List<Product>> viewAllProductsHandler() throws ProductException, UserException{
		
		return new ResponseEntity<>(productService.viewAllProducts(),HttpStatus.OK);
	}
}
