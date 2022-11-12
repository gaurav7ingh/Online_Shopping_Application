package com.shopping.service;

import com.shopping.exception.ProductException;
import com.shopping.model.Product;

public interface AdminService{
	
	public Product addProduct(Product p) throws ProductException;
	
	public Product updateProduct(Product p) throws ProductException;
	
	public Product removeProduct(Integer pid) throws ProductException;
	
}
