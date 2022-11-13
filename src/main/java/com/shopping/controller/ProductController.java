package com.shopping.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

	private String currentSession;

	public void setCurrentSession(String uuid) {
		this.currentSession = uuid;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> viewAllProductsHandler(@RequestParam String uuid) throws ProductException, UserException, LoginException{
		
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Product> viewAllProduct = productService.viewAllProducts();
		
		return new ResponseEntity<List<Product>>(viewAllProduct,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> viewProductHandler(@PathVariable("id")Integer id,@RequestParam String uuid) throws ProductException, LoginException{
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Product viewProduct = productService.viewProduct(id);
		
		return new ResponseEntity<Product>(viewProduct,HttpStatus.OK);
	}
	
	@GetMapping("/productByCategory/{cname}")
	public ResponseEntity<List<Product>> viewProductByCategoryHandler(@PathVariable String cname,@RequestParam String uuid) throws LoginException, ProductException{
		this.setCurrentSession(uuid);

		if (currentSession == null)
			throw new LoginException("user is not logged in");

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Product> productbyCatagory = productService.viewProductByCategory(cname);
		
		return new ResponseEntity<List<Product>>(productbyCatagory,HttpStatus.OK);
	}
	
}
