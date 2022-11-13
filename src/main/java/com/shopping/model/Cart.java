package com.shopping.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;


	@ElementCollection
	private Map<Product, Integer> Products = new HashMap<>();


	public Integer getCartId() {
		return cartId;
	}


	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Map<Product, Integer> getProducts() {
		return Products;
	}

	
	@Bean(value = "products")
	public void setProducts(Map<Product, Integer> products) {
		Products = products;
	}

	
}
