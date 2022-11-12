package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Customer;
import com.shopping.model.User;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	public Customer findByemail(String email);
	
}
