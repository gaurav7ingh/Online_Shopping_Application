package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CartException;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cRepo;

	@Override
	public Cart addProductToCart(Cart c, Product p, Integer quantity) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeProductFromCart(Cart c, Product p) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart updateProductQuantity(Cart c, Product p, Integer quantity) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeAllProduct(Cart c) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart viewAllProduct(Cart c) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

}
