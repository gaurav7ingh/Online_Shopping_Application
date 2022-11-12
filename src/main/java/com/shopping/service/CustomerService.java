package com.shopping.service;

import java.util.List;

import com.shopping.exception.CustomerException;
import com.shopping.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer cust) throws CustomerException;

	public Customer updateCustomer(Customer cust,String key) throws CustomerException;

	public Customer removeCustomer(String email) throws CustomerException;

	public Customer viewCustomer(String email) throws CustomerException;

	public List<Customer> ViewAllCustomers() throws CustomerException;
}
