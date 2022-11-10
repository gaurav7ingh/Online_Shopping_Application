package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.repository.CustomerRepo;

public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo custRepo;
	
	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
		// TODO Auto-generated method stub
		Customer savedCustomer = custRepo.save(cust);
		if(savedCustomer!=null) {
			return savedCustomer;
		}
		else {
			throw new CustomerException("Customer not saved!.....");
		}
	}

	@Override
	public Customer updateCustomer(Customer cust) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer removeCustomer(Customer cust) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomer(Customer cust) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> ViewAllCustomers(String location) throws CustomerException {
		// TODO Auto-generated method stub
		
		List<Customer> viewAllCust = custRepo.findAll();
		if(viewAllCust.size()==0) {
			throw new CustomerException("No Customer are there");
		}
		else {
			return viewAllCust;
		}
		
	}

}
