package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByemail(String email);
	
}
