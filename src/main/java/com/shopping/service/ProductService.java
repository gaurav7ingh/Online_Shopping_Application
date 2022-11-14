package com.shopping.service;

import java.util.List;

import com.shopping.exception.ProductException;
import com.shopping.model.Product;

public interface ProductService {

	public List<Product> viewAllProducts() throws ProductException;

	public Product viewProduct(Integer id) throws ProductException;

	public List<Product> viewProductByCategory(String cname) throws ProductException;

	public List<Product> viewProductByProductName(String productName) throws ProductException;
}
