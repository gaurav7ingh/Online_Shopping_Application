package com.shopping.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.shopping.exception.CustomerException;
import com.shopping.exception.LoginException;
import com.shopping.exception.OrderException;
import com.shopping.exception.ProductException;
import com.shopping.model.Orders;
import com.shopping.service.LogInService;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderContoller {

	@Autowired
	private OrderService oSer;

	@Autowired
	private LogInService logService;

	@PostMapping
	public ResponseEntity<Orders> addOrderHandler(@Valid @RequestBody Orders orders, @RequestParam(required = true) Integer id,
			@RequestParam(required = true) String location, @RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.addOrders(orders, id, location);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}

	@PutMapping
	public ResponseEntity<Orders> updateOrderHandler(@RequestBody Orders orders,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.updateOrders(orders);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<Orders> removeOrderHandler(@RequestBody Orders orders,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.removeOrder(orders);

		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Orders> viewOrderHandler(@PathVariable Integer ordersId,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");
		
		Orders order = oSer.viewOrder(ordersId);

		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	@GetMapping("/dates/{date}")
	public ResponseEntity<List<Orders>> viewAllOrderHandler(@PathVariable(value = "date") LocalDate date,
			@RequestParam String uuid) throws OrderException, CustomerException, ProductException, LoginException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Orders> orders = oSer.viewAllOrders(date);

		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<List<Orders>> viewAllOrderByIDHandler(@PathVariable(value = "customerId") Integer customerId,
			@RequestParam String uuid) throws OrderException, CustomerException, ProductException, LoginException {
		
		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Orders> orders = oSer.viewAllOrdersByUserId(customerId);

		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}

}