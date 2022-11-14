package com.shopping.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.shopping.exception.AddressException;
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
	public ResponseEntity<Orders> addOrderHandler(@Valid @RequestBody Orders orders,
			@RequestParam(required = true) Integer customerId, @RequestParam(required = true) Integer addressId,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException, AddressException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.addOrders(orders, customerId, addressId);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<Orders> removeOrderHandler(@RequestParam Integer orderId,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.viewOrder(orderId);
		order = oSer.removeOrder(order);

		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	@PutMapping
	public ResponseEntity<Orders> updateOrderHandler(@RequestParam Integer orderId,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.viewOrder(orderId);
		order = oSer.updateOrders(order);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<List<Orders>> viewAllOrderByIDHandler(@PathVariable(value = "customerId") Integer customerId,
			@RequestParam String uuid) throws OrderException, CustomerException, ProductException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		List<Orders> orders = oSer.viewAllOrdersByUserId(customerId);

		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}

	@GetMapping("/dates/{dd-MM-yyyy}")
	public ResponseEntity<List<Orders>> viewAllOrderHandler(@PathVariable("dd-MM-yyyy") String date,
			@RequestParam String uuid) throws OrderException, CustomerException, ProductException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

		LocalDate localDate = LocalDate.parse(date, formatter);

		List<Orders> orders = oSer.viewAllOrders(localDate);

		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<Orders> viewOrderHandler(@RequestParam Integer ordersId,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {

		if (!logService.loggedInOrNot(uuid))
			throw new LoginException("This user is not logged in");

		Orders order = oSer.viewOrder(ordersId);

		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

}