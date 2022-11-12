package com.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.exception.ProductException;
import com.shopping.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> viewAllProducts() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product viewProduct(int id) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> viewProductByCategory(String cname) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product removeProduct(int pid) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
