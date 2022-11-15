package com.shopping.service;

import com.shopping.exception.ProductException;
import com.shopping.exception.UserException;
import com.shopping.model.Product;

public interface AdminService {

	public Product addProduct(Product p) throws ProductException;

	public Product removeProduct(Integer pid, Integer sellerId) throws ProductException, UserException;

	public Product updateProduct(Product p) throws ProductException;

}
