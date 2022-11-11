package com.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
import com.shopping.repository.CustomerRepo;

@Service
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
		
	 Optional<Customer> opt = custRepo.findById(cust.getCustomerId());
	 if(opt.isPresent()) {
		 Customer updatedcustomer = custRepo.save(cust);
		 return updatedcustomer;
	 }
	 else {
		 throw new CustomerException("Invalid Details of Customer!......");
	 }		
	}

	@Override
	public Customer removeCustomer(Customer cust) throws CustomerException {
		// TODO Auto-generated method stub
	
		Optional<Customer> opt = custRepo.findById(cust.getCustomerId());
		 if(opt.isPresent()) {
			 Customer deletedcustomer = opt.get();
			 custRepo.delete(cust);
			 return deletedcustomer;
		 }
		 else {
			 throw new CustomerException("Invalid Details of Customer!......");
		 }
		
	}

	@Override
	public Customer viewCustomer(Customer cust) throws CustomerException {
		// TODO Auto-generated method stub
		
		Optional<Customer> opt = custRepo.findById(cust.getCustomerId());
		 if(opt.isPresent()) {
			 Customer viewCustomer = opt.get();
			 return viewCustomer;
		 }
		 else {
			 throw new CustomerException("Invalid Details of Customer!......");
		 }
		
		
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
