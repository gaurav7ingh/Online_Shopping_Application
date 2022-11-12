package com.shopping.service;

import java.time.LocalDate;
import java.util.List;
import com.shopping.exception.OrderException;
import com.shopping.model.Orders;

public interface OrderService {

	public Orders addOrders(Orders orders) throws OrderException;
	
	public Orders updateOrders(Orders orders) throws OrderException;
	
	public Orders removeOrder(Orders orders) throws OrderException;
	
	public Orders viewOrder(Orders orders) throws OrderException;
	
	public List<Orders> viewAllOrders(LocalDate date) throws OrderException;
	
	public List<Orders> viewAllOrdersByLocation(String location) throws OrderException;
	
	public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException;
	
}
