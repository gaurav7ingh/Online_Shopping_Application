package com.shopping.service;
<<<<<<< HEAD
=======

import java.util.List;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;
>>>>>>> 5ae2891e0847388e96a1c623aab42dfacf495bfb

public interface CustomerService {

	public Customer addCustomer(Customer cust)throws CustomerException;
	
	public Customer updateCustomer(Customer cust)throws CustomerException;
	
	public Customer removeCustomer(Customer cust)throws CustomerException;
	
	public Customer viewCustomer(Customer cust)throws CustomerException;
	
	public List<Customer> ViewAllCustomers(String location)throws CustomerException;
}
