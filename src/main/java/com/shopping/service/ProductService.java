package com.shopping.service;

import java.util.List;

import com.shopping.exception.ProductException;
import com.shopping.model.Product;

public interface ProductService {

    public List<Product> viewAllProducts()throws ProductException;
	
	public Product addProduct(Product product)throws ProductException;
	
	public Product updateProduct(Product product)throws ProductException;
	
	public Product viewProduct(int id)throws ProductException;
	
	public List<Product> viewProductByCategory(String cname)throws ProductException;
	
	public Product removeProduct(int pid)throws ProductException;
	
}
