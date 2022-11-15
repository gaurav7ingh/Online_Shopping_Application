package com.shopping.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.shopping.model.CurrentUserSession;
import com.shopping.model.Orders;
import com.shopping.service.LogInService;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderContoller {

	private OrderService oSer;

	private LogInService logService;

	private CurrentUserSession cs;

	@PostMapping
	public ResponseEntity<Orders> addOrderHandler(@Valid @RequestBody Orders orders, @RequestParam Integer addressId,
			@RequestParam String uuid)
			throws LoginException, OrderException, CustomerException, ProductException, AddressException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Integer customerId = cs.getUserId();

		Orders order = oSer.addOrders(orders, customerId, addressId);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}

	@PutMapping("/return")
	public ResponseEntity<Orders> returnOrderHandler(@RequestParam Integer orderId, @RequestParam String uuid)
			throws LoginException, OrderException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Orders order = oSer.viewOrder(orderId);
		order = oSer.removeOrder(order);

		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	@PutMapping
	public ResponseEntity<Orders> cancelOrderHandler(@RequestParam Integer orderId, @RequestParam String uuid)
			throws OrderException, LoginException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Orders order = oSer.viewOrder(orderId);
		order = oSer.updateOrders(order);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<List<Orders>> viewAllOrderHandler(@RequestParam String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Integer customerId = cs.getUserId();

		List<Orders> orders = oSer.viewAllOrdersByUserId(customerId);

		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}

	@GetMapping("/dates/{dd-MM-yyyy}")
	public ResponseEntity<List<Orders>> viewAllOrderHandler(@PathVariable("dd-MM-yyyy") String date,
			@RequestParam String uuid) throws OrderException, LoginException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

		LocalDate localDate = LocalDate.parse(date, formatter);

		List<Orders> orders = oSer.viewAllOrders(localDate);

		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}

	@GetMapping("/orders")
	public ResponseEntity<Orders> viewOrderByOrderIDHandler(@RequestParam Integer ordersId,
			@RequestParam(required = true) String uuid)
			throws OrderException, CustomerException, ProductException, LoginException {

		this.setCs(logService.getSessionByUuid(uuid));

		if (cs == null)
			throw new LoginException("This user is not logged in");

		Orders order = oSer.viewOrder(ordersId);

		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	@Autowired
	public void setoSer(OrderService oSer) {
		this.oSer = oSer;
	}

	@Autowired
	public void setLogService(LogInService logService) {
		this.logService = logService;
	}

	public void setCs(CurrentUserSession cs) {
		this.cs = cs;
	}

}