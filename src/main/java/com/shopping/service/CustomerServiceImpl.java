package com.shopping.service;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
=======

>>>>>>> 369c430a38d5d4c7f2ba19cfd6e1fdf7e29b8f1f

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo custRepo;

	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {

		Customer existingCustomer = custRepo.findByEmail(cust.getEmail());
		if(existingCustomer!=null) {
			throw new CustomerException("Customer Already Registered with Email Id");
		}
		Customer savedCustomer = custRepo.save(cust);
		if (savedCustomer != null) {
			return savedCustomer;
		} else {
			throw new CustomerException("Customer not saved!.....");
		}
	}

	@Override
	public Customer updateCustomer(Customer cust) throws CustomerException {
		Optional<Customer> optional = custRepo.findById(cust.getCustomerId());

		if (!optional.isPresent())
			throw new CustomerException("No customer exists with this information");

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
	public Customer viewCustomer(Customer cust) throws CustomerException {
		Optional<Customer> optional = custRepo.findById(cust.getCustomerId());
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
