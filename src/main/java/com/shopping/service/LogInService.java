package com.shopping.service;

import com.shopping.exception.LoginException;
import com.shopping.exception.UserException;
import com.shopping.model.CurrentUserSession;
import com.shopping.model.User;
import com.shopping.model.UserDTO;

public interface LogInService {

	public User addUser(User user) throws UserException;

	public boolean adminOrNot(String uuid) throws UserException;

	public boolean loggedInOrNot(String uuid) throws LoginException;

	public CurrentUserSession loginUser(UserDTO dto) throws UserException;

	public User removeUser(User user) throws UserException;

	public String signOutUser(String key) throws UserException;
}
