package com.shopping.controller;

import java.util.Map;

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
import com.shopping.exception.CartException;
import com.shopping.exception.CustomerException;
import com.shopping.exception.LoginException;
import com.shopping.exception.ProductException;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.service.CartService;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/carts")
public class CartController {

	@Autowired
	private CartService cSer;

	@Autowired
	private LogInService logService;

	@PostMapping("/{cartId}/{quantity}")
	public ResponseEntity<Cart> addNewProduct(@PathVariable(value = "cartId") Integer cartId, @Valid @RequestBody Product p,
			@PathVariable(value = "quantity") Integer quantity, @RequestParam String uuid)
			throws AddressException, CustomerException, CartException, LoginException, ProductException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Cart cart = cSer.getCartById(cartId);

		Cart cc = cSer.addProductToCart(cart, p, quantity);

		return new ResponseEntity<Cart>(cc, HttpStatus.OK);
	}

	@DeleteMapping("/products")
	public ResponseEntity<Cart> removeProduct(@PathVariable Integer cartId, @RequestBody Product p,
			@RequestParam String uuid)
			throws AddressException, CustomerException, CartException, LoginException, ProductException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Cart cart = cSer.getCartById(cartId);

		Cart cc = cSer.removeProductFromCart(cart, p);

		return new ResponseEntity<Cart>(cc, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{cartId}")
	public ResponseEntity<Cart> updateProductQuantity(@PathVariable("cartId") Integer cartId, @RequestBody Product p,
			@RequestParam Integer quantity, @RequestParam String uuid)
			throws AddressException, CustomerException, CartException, LoginException, ProductException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Cart cart = cSer.getCartById(cartId);

		Cart cc = cSer.updateProductQuantity(cart, p, quantity);
		return new ResponseEntity<Cart>(cc, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Cart> removeAllProduct(@PathVariable Integer cartId, @RequestParam String uuid)
			throws AddressException, CustomerException, CartException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Cart cart = cSer.getCartById(cartId);

		Cart cc = cSer.removeAllProduct(cart);

		return new ResponseEntity<Cart>(cc, HttpStatus.OK);
	}

	@GetMapping("/products/{cartId}")
	public ResponseEntity<Map<Product, Integer>> viewAllProduct(@PathVariable Integer cartId, @RequestParam String uuid)
			throws AddressException, CustomerException, CartException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Cart cart = cSer.getCartById(cartId);

		Map<Product, Integer> map = cSer.viewAllProduct(cart);
		return new ResponseEntity<Map<Product, Integer>>(map, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Integer customerId, @RequestParam String uuid)
			throws LoginException, CartException, CustomerException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Cart cart = cSer.getCartByCustomerId(customerId);

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

}
