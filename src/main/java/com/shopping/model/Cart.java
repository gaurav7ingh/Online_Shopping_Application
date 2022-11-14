package com.shopping.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.context.annotation.Bean;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name = "cart_id")
	private Integer cartId;

	@ElementCollection
	private Map<Product, Integer> Products = new HashMap<>();

	public Integer getCartId() {
		return cartId;
	}

	public Map<Product, Integer> getProducts() {
		return Products;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	@Bean(value = "products")
	public void setProducts(Map<Product, Integer> products) {
		Products = products;
	}

}
