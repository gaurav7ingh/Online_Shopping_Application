package com.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.UserException;
import com.shopping.model.CurrentUserSession;
import com.shopping.model.User;
import com.shopping.model.UserDTO;
import com.shopping.repository.CurrentUserSessionRepo;
import com.shopping.repository.UserRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LogInService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Override
	public User addUser(User user) throws UserException {
         User existingUser = uRepo.findByemail(user.getEmail());
		
		if(existingUser!=null) {
			throw new UserException("User Already Registered with Mobile number");
		}
		else {
			return uRepo.save(user);
		}
	}

	@Override
	public User removeUser(User user) throws UserException {
		// TODO Auto-generated method stub
	    
        User existingUser = uRepo.findByemail(user.getEmail());
		
		if(existingUser!=null) {
			uRepo.delete(existingUser);
			return existingUser;
		}
		else {
			throw new UserException("User Unavailable to found!.....");
		}
	}

	@Override
	public String validateUser(UserDTO dto) throws UserException {
		// TODO Auto-generated method stub
		 User existingUser = uRepo.findByemail(dto.getEmail());
			
			if(existingUser==null) {
				throw new UserException("Please Enter a valid email Id");
			}
			
			Optional<CurrentUserSession> validUserSesssionOpt = currRepo.findById(existingUser.getUserId());
			
			if(validUserSesssionOpt.isPresent()) {
				throw new UserException("User Already Logged In with this email Id");
			}
			if(existingUser.getPassword().equals(dto.getPassword())) {
				String key = RandomString.make(6);
				
				CurrentUserSession currentUserSession = new CurrentUserSession();
				currRepo.save(currentUserSession);
				return currentUserSession.toString();
				
			}
			else {
				throw new UserException("Please Enter a Valid Password");
			}
	}

	@Override
	public String signOutUser(String key) throws UserException {
		// TODO Auto-generated method stub
        CurrentUserSession validUserSession = currRepo.findByUuid(key);
		
		if(validUserSession==null) {
			throw new UserException("User Not Logged In with this email Id");
		}
		currRepo.delete(validUserSession);
		return "Sign Out Successfully";
	}

	
	

}
