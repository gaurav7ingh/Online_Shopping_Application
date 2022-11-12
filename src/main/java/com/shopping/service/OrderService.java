package com.shopping.service;

import java.time.LocalDate;
import java.util.List;

import com.shopping.exception.CustomerException;
import com.shopping.exception.OrderException;
import com.shopping.exception.ProductException;
import com.shopping.model.Orders;

public interface OrderService {

	public Orders addOrders(Orders orders,Integer customerId,String location) throws OrderException, CustomerException, ProductException;
	
	public Orders updateOrders(Orders orders) throws OrderException;
	
	public Orders removeOrder(Orders orders) throws OrderException;
	
	public Orders viewOrder(Orders orders) throws OrderException;
	
	public List<Orders> viewAllOrders(LocalDate date) throws OrderException;
	
	public List<Orders> viewAllOrdersByLocation(String location) throws OrderException;
	
	public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException, CustomerException;
	
}
