package com.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CustomerException;
import com.shopping.exception.UserException;
import com.shopping.model.Customer;
import com.shopping.model.User;
import com.shopping.repository.CustomerRepo;
import com.shopping.repository.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Customer addCustomer(Customer cust) throws CustomerException, UserException {

		Customer existingCustomer = custRepo.findByEmail(cust.getEmail());
		if (existingCustomer != null)
			throw new CustomerException("Customer Already Registered with Email...!");

		User user = userRepo.findByEmail(cust.getEmail());
		if (user == null)
			throw new UserException("First register the user...! \n no user regiseterd with this email.");

		cust.setCustomerId(user.getUserId());
		Customer savedCustomer = custRepo.save(cust);
		
		return savedCustomer;

	}

	@Override
	public Customer updateCustomer(Customer cust) throws CustomerException {
		Optional<Customer> optional = custRepo.findById(cust.getCustomerId());
		if (!optional.isPresent())
			throw new CustomerException("No customer exists with this information");

		User user = userRepo.findById(cust.getCustomerId()).orElseThrow(()->new CustomerException("No user exist please add this user first"));
		
		if(cust.getEmail() != user.getEmail()) {
			user.setEmail(cust.getEmail());
			userRepo.save(user);
		}
		Customer customer = custRepo.save(cust);
		if (customer == null)
			throw new CustomerException("customer not updated");
		return customer;
	}

	@Override
	public Customer removeCustomer(Customer cust) throws CustomerException {
		Optional<Customer> cus = custRepo.findById(cust.getCustomerId());
		if (!cus.isPresent())
			throw new CustomerException("This customer doesn't exist");
		custRepo.delete(cust);
		return cus.get();
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> optional = custRepo.findById(customerId);
		if (!optional.isPresent())
			throw new CustomerException("Customer not found");
		return optional.get();
	}

	@Override
	public List<Customer> ViewAllCustomers(String location) throws CustomerException {

		List<Customer> viewAllCust = custRepo.findAll();
		if (viewAllCust.size() == 0) {
			throw new CustomerException("No Customer are there");
		} else {
			return viewAllCust;
		}

	}

}
