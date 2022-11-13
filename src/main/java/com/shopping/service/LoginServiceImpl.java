package com.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.LoginException;
import com.shopping.exception.UserException;
import com.shopping.model.CurrentUserSession;
import com.shopping.model.User;
import com.shopping.model.UserDTO;
import com.shopping.repository.CurrentUserSessionRepo;
import com.shopping.repository.UserRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LogInService {

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo currRepo;

	@Override
	public User addUser(User u) throws UserException {
		User user = uRepo.findByEmail(u.getEmail());
		if (!(user == null))
			throw new UserException("User is already registerd");
		user = uRepo.save(u);
		return user;
	}

	@Override
	public User removeUser(User user) throws UserException {

		User existingUser = uRepo.findByEmail(user.getEmail());

		if (existingUser == null) {
			uRepo.delete(existingUser);
			return existingUser;
		} else {
			throw new UserException("User Unavailable to found!.....");
		}
	}

	@Override
	public String loginUser(UserDTO dto) throws UserException {

		User existingUser = uRepo.findByEmail(dto.getEmail());

		if (existingUser == null) {
			throw new UserException("Please Enter a valid email Id");
		}

		Optional<CurrentUserSession> validUserSesssionOpt = currRepo.findById(existingUser.getUserId());

		if (validUserSesssionOpt.isPresent()) {
			throw new UserException("User Already Logged In with this email Id");
		}
		if (existingUser.getPassword().equals(dto.getPassword())) {
			String key = RandomString.make(6);

			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setUserId(existingUser.getUserId());
			currentUserSession.setRole(existingUser.getRole());
			currentUserSession.setEmail(existingUser.getEmail());
			currentUserSession.setUuid(key);

			currRepo.save(currentUserSession);
			return currentUserSession.toString();

		} else {
			throw new UserException("Please Enter a Valid Password");
		}
	}

	@Override
	public String signOutUser(String key) throws UserException {

		CurrentUserSession validUserSession = currRepo.findByUuid(key);

		if (validUserSession == null) {
			throw new UserException("User Not Logged In with this email Id");
		}
		currRepo.delete(validUserSession);
		return "Sign Out Successfully";
	}

	@Override
	public boolean loggedInOrNot(String uuid) throws LoginException {
		CurrentUserSession curr = currRepo.findByUuid(uuid);
		if (curr == null)
			throw new LoginException("user is not logged in");
		return true;
	}

	@Override
	public boolean adminOrNot(String uuid) throws UserException {
		CurrentUserSession curr = currRepo.findByUuid(uuid);
		if (curr.getRole().equalsIgnoreCase("admin"))
			return true;
		throw new UserException("You are not allowed...!");
	}

}
