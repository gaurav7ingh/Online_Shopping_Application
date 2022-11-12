package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.CustomerService;

@RestController
@RequestMapping("/orders")
public class OrderContoller {
	
	@Autowired
	private CustomerService cService;

	

}
