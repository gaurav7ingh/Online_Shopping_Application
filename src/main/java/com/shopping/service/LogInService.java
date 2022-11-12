package com.shopping.service;

import com.shopping.exception.UserException;
import com.shopping.model.User;
import com.shopping.model.UserDTO;

public interface LogInService {

	public User addUser(User user) throws UserException;

	public User removeUser(User user) throws UserException;

	public String validateUser(UserDTO dto) throws UserException;

	public String signOutUser(String key) throws UserException;

}
