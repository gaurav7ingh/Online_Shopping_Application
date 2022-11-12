package com.shopping.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.exception.OrderException;
import com.shopping.model.Orders;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public Orders addOrders(Orders orders) throws OrderException {
		return null;
	}

	@Override
	public Orders updateOrders(Orders orders) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders removeOrder(Orders orders) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders viewOrder(Orders orders) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> viewAllOrders(LocalDate date) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> viewAllOrdersByLocation(String location) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
