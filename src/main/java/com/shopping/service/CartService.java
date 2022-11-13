package com.shopping.service;

import java.util.Map;

import com.shopping.exception.CartException;
import com.shopping.exception.CustomerException;
import com.shopping.exception.ProductException;
import com.shopping.model.Cart;
import com.shopping.model.Product;

public interface CartService {
	public Cart addProductToCart(Cart c, Product p, Integer quantity) throws CartException,ProductException;

	public Cart removeProductFromCart(Cart c, Product p) throws CartException,ProductException;

	public Cart updateProductQuantity(Cart c, Product p, Integer quantity) throws CartException,ProductException;

	public Cart removeAllProduct(Cart c) throws CartException;

	public Map<Product, Integer> viewAllProduct(Cart c) throws CartException;
	
	public Cart getCartById(Integer cartId) throws CartException;
	
	public Cart getCartByCustomerId(Integer customerId) throws CartException,CustomerException;
}