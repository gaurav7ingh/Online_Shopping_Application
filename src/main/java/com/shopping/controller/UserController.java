package com.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.UserException;
import com.shopping.model.User;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private LogInService logService;

	@PostMapping
	public ResponseEntity<User> addUserHandler(@Valid @RequestBody User user) throws UserException {
		
		User saveduser = logService.addUser(user);
		
		return new ResponseEntity<User>(saveduser, HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<User> deleteUserHandler(@RequestBody User user) throws UserException {
		
		User deleteuser = logService.removeUser(user);
		
		return new ResponseEntity<User>(deleteuser, HttpStatus.ACCEPTED);
	}

	
}
