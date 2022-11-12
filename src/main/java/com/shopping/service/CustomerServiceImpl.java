package com.shopping.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CustomerException;
import com.shopping.model.Address;
import com.shopping.model.CurrentUserSession;
import com.shopping.model.Customer;
import com.shopping.repository.CurrentUserSessionRepo;
import com.shopping.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;

	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
		
		Customer existingCustomer = custRepo.findByemail(cust.getEmail());
		
		Set<Address> addr = cust.getAddresses();
		
		if(existingCustomer!=null) {
			throw new CustomerException("Customer Already Registered with Email Id");
		}
		return custRepo.save(cust);
		
	}

	@Override
	public Customer updateCustomer(Customer cust, String key) throws CustomerException {
		// TODO Auto-generated method stub
	
		CurrentUserSession loggedInUser  = currRepo.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new CustomerException("Please provide a valid key to update a customer");	
		}
		if(cust.getCustomerId()==loggedInUser.getUserId()) {
			return custRepo.save(cust);
		}
		else {
			throw new CustomerException("Invalid Customer Details, please login first");
		}
		
	}

	@Override
	public Customer removeCustomer(String email) throws CustomerException {
		Customer cus = custRepo.findByemail(email);
		
		if(cus!=null) {
			custRepo.delete(cus);
			return cus;
		}
		else 
		throw new CustomerException("This customer doesn't exist");
		
	}

	@Override
	public Customer viewCustomer(String email) throws CustomerException {
		// TODO Auto-generated method stub
        Customer existingCustomer = custRepo.findByemail(email);
		
		if(existingCustomer!=null) {
			return existingCustomer;
		}
		else {
			throw new CustomerException("This customer doesn't exist");
		}
		
	}

	@Override
	public List<Customer> ViewAllCustomers() throws CustomerException {
		// TODO Auto-generated method stub
		List<Customer> viewAllCust = custRepo.findAll();
		if (viewAllCust.size() == 0) {
		throw new CustomerException("No Customer are there");
		} else {
			return viewAllCust;
		}
	}
}
