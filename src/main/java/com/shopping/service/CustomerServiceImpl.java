package com.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.repository.CustomerRepo;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo custRepo;

	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
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
