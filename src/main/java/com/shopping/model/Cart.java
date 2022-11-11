package com.shopping.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

@Entity
public class Cart {
	private String cartId;
	private Customer customer;
	private Map<Product, Integer> product = new HashMap<>();

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<Product, Integer> getProduct() {
		return product;
	}

	public void setProduct(Map<Product, Integer> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", product=" + product + "]";
	}

}
