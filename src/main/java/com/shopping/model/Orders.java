package com.shopping.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Validated
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@NotNull
	private LocalDate orderDate;

	@NotBlank
	@NotNull
	@NotEmpty
	private String orderStatus;

	private Integer addressId;

	private String location;

//	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "ordered_product", joinColumns = @JoinColumn(name = "order_id"))
	@Column(name = "product_id")
	private Map<Product, Integer> orderedProducts = new HashMap<>();

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public Integer getAddressId() {
		return addressId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getLocation() {
		return location;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public Map<Product, Integer> getOrderedProducts() {
		return orderedProducts;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderedProducts(Map<Product, Integer> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
