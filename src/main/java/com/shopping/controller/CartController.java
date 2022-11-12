package com.shopping.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.AddressException;
import com.shopping.exception.CartException;
import com.shopping.exception.CustomerException;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.service.CartService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	private CartService cSer;

	@PostMapping("/{id}")
	public ResponseEntity<Cart> saveCartAdd(@RequestBody Cart c, @RequestBody Product p,
			@PathVariable(value = "id") Integer quantity) throws AddressException, CustomerException, CartException {

		return new ResponseEntity<Cart>(cSer.addProductToCart(c, p, quantity), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Cart> removeProduct(@RequestBody Cart c, @RequestBody Product p)
			throws AddressException, CustomerException, CartException {

		return new ResponseEntity<Cart>(cSer.removeProductFromCart(c, p), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{quan}")
	public ResponseEntity<Cart> updateProductQuantity(@RequestBody Cart c, @RequestBody Product p,
			@PathVariable(value = "quan") Integer quantity) throws AddressException, CustomerException, CartException {

		return new ResponseEntity<Cart>(cSer.updateProductQuantity(c, p, quantity), HttpStatus.OK);
	}

	@PostMapping("/removeAll")
	public ResponseEntity<Cart> removeAllProduct(@RequestBody Cart c)
			throws AddressException, CustomerException, CartException {

		return new ResponseEntity<Cart>(cSer.removeAllProduct(c), HttpStatus.OK);
	}

	@PostMapping("/GET")
	public ResponseEntity<Map<Product, Integer>> viewAllProduct(@RequestBody Cart c)
			throws AddressException, CustomerException, CartException {

		return new ResponseEntity<Map<Product, Integer>>(cSer.viewAllProduct(c), HttpStatus.OK);
	}
}
