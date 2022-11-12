package com.shopping.service;

import javax.security.auth.login.LoginException;

import com.shopping.exception.UserException;
import com.shopping.model.User;
import com.shopping.model.UserDTO;

public interface LogInService {

	public User addUser(User user) throws UserException;

	public User removeUser(User user) throws UserException;

	public String loginUser(UserDTO dto) throws UserException;

	public String signOutUser(String key) throws UserException;
	
	public boolean loggedInOrNot(String uuid) throws LoginException;
}
