package com.shopping.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.AddressException;
import com.shopping.exception.CustomerException;
import com.shopping.exception.OrderException;
import com.shopping.exception.ProductException;
import com.shopping.model.Address;
import com.shopping.model.Customer;
import com.shopping.model.Orders;
import com.shopping.model.Product;
import com.shopping.repository.AddressDao;
import com.shopping.repository.CustomerRepo;
import com.shopping.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private CustomerRepo cusomerRepo;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ProductService productService;

	@Autowired
	private AdminService adminService;

	@Override
	public Orders addOrders(Orders orders, Integer id, Integer addressId)
			throws OrderException, CustomerException, ProductException, AddressException {

		Customer customer = cusomerRepo.findById(id).get();
		if (customer == null)
			throw new CustomerException("please provide the right customer details...!");

		Map<Product, Integer> products = customer.getCart().getProducts();

		for (var map : products.entrySet()) {
			Product product = productService.viewProduct(map.getKey().getProductId());
			product.setQuantity(product.getQuantity() - map.getValue());
			adminService.updateProduct(product);
		}

		if (products.isEmpty())
			throw new ProductException("No product found for order...!");

		orders.setOrderDate(LocalDate.now());
		orders.getOrderedProducts().putAll(products);
		orders.setCustomer(customer);
		orders.setOrderStatus("open");
		Address add = addressDao.findById(addressId)
				.orElseThrow(() -> new AddressException("Please provid the address id...!"));
		orders.setAddressId(add.getAddressId());
		orders.setLocation(add.getState());
		Orders order = oRepo.save(orders);
		if (order != null)
			customer.getCart().setProducts(new HashMap<Product, Integer>());
		return order;
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
	public Orders updateOrders(Orders orders) throws OrderException {
		Orders order = oRepo.findById(orders.getOrderId())
				.orElseThrow(() -> new OrderException("No orders found for update"));

		if (order.getOrderStatus().equalsIgnoreCase("open"))
			order.setOrderStatus("cancelled");
		else if (order.getOrderStatus().equalsIgnoreCase("cancelled"))
			throw new OrderException("This order is already cancelled");
		else if (order.getOrderStatus().equalsIgnoreCase("placed"))
			throw new OrderException("Order is reached to the destination \n we can't update it");

		Orders newOrder = oRepo.save(order);
		return newOrder;
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

	@Override
	public Orders viewOrder(Integer ordersId) throws OrderException {
		Orders order = oRepo.findById(ordersId).orElseThrow(() -> new OrderException("No order found...!"));
		return order;
	}

}
