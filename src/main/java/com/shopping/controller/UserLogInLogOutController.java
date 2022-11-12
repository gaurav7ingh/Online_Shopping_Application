package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.exception.UserException;
import com.shopping.model.User;
import com.shopping.model.UserDTO;
import com.shopping.service.LogInService;

@RestController
@RequestMapping("/users")
public class UserLogInLogOutController {

	@Autowired
	private LogInService logService;

	@PostMapping
	public ResponseEntity<User> addUserHandler(@RequestBody User user) throws UserException {
		User saveduser = logService.addUser(user);
		return new ResponseEntity<User>(saveduser, HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<User> deleteUserHandler(@RequestBody User user) throws UserException {

		User deleteuser = logService.removeUser(user);
		return new ResponseEntity<User>(deleteuser, HttpStatus.ACCEPTED);
	}

	@PostMapping("/validate")
	public ResponseEntity<String> logInHandler(@RequestBody UserDTO dto) throws UserException {
		String result = logService.loginUser(dto);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<String> logoutuserHandler(@PathVariable("uuid") String uuid) throws UserException {

		String log = logService.signOutUser(uuid);
		return new ResponseEntity<String>(log, HttpStatus.OK);
	}

}
