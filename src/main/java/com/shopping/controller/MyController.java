package com.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MyController {

	//just for tasting the swagger
	@GetMapping("/{name}")
	public ResponseEntity<String> sayHelloTo(@PathVariable String name){
		return new ResponseEntity<>("Hello "+name,HttpStatus.OK);
	}
	
}
