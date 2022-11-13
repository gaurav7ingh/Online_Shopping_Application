package com.shopping.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CustomerException;
import com.shopping.exception.OrderException;
import com.shopping.exception.ProductException;
import com.shopping.model.Customer;
import com.shopping.model.Orders;
import com.shopping.model.Product;
import com.shopping.repository.CustomerRepo;
import com.shopping.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private CustomerRepo cusomerRepo;

	@Override
	public Orders addOrders(Orders orders, Integer id,String location) throws OrderException, CustomerException, ProductException {

		Customer customer = cusomerRepo.findById(id).get();
		if (customer == null)
			throw new CustomerException("please provide the right customer details...!");

		Map<Product, Integer> products = customer.getCart().getProducts();

		if (products.isEmpty())
			throw new ProductException("No product found for order...!");

		orders.setOrderDate(LocalDate.now());
		orders.setProducts(products);
		orders.setCustomer(customer);
		orders.setOrderStatus("open");
		orders.setLocation(location);

		Orders order = oRepo.save(orders);

		return order;
	}

	@Override
	public Orders updateOrders(Orders orders) throws OrderException {
		Orders order = oRepo.findById(orders.getOrderId())
				.orElseThrow(() -> new OrderException("No orders found for update"));

		if (order.getOrderStatus().equals("open"))
			order.setOrderStatus("cancelled");
		if (order.getOrderStatus().equals("cancelled"))
			throw new OrderException("This order is already cancelled");
		if (order.getOrderStatus().equals("placed"))
			throw new OrderException("Order is reached to the destination \n we can't update it");

		Orders newOrder = oRepo.save(order);
		return newOrder;
	}

	@Override
	public Orders removeOrder(Orders orders) throws OrderException {
		Orders order = oRepo.findById(orders.getOrderId())
				.orElseThrow(() -> new OrderException("No orders found for undo"));
		if (!LocalDate.now().minusDays(7).isBefore(order.getOrderDate()))
			throw new OrderException("You are excided the time limit for replacement");
		order.setOrderStatus("returned");

		Orders orders2 = oRepo.save(order);

		return orders2;
	}

	@Override
	public Orders viewOrder(Integer ordersId) throws OrderException {
		Orders order = oRepo.findById(ordersId).orElseThrow(() -> new OrderException("No order found...!"));
		return order;
	}

	@Override
	public List<Orders> viewAllOrders(LocalDate date) throws OrderException {
		List<Orders> orders = oRepo.findByOrderDate(date);
		if (orders.isEmpty())
			throw new OrderException("No order found on this date...!");
		return orders;
	}

	@Override
	public List<Orders> viewAllOrdersByLocation(String location) throws OrderException {
		List<Orders> orders = oRepo.findByLocation(location);
		if (orders.isEmpty())
			throw new OrderException("No order found on this address...!");
		return orders;
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(Integer customerId) throws OrderException, CustomerException {
		Customer customer = cusomerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("this user doesn't exist"));
		List<Orders> orders = oRepo.findByCustomer(customer);
		if (orders.isEmpty())
			throw new OrderException("No order found for this customer...!");
		return orders;
	}

}
