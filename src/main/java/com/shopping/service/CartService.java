package com.shopping.service;

import com.shopping.exception.CartException;
import com.shopping.model.Cart;
import com.shopping.model.Product;

public interface CartService {
	public Cart addProductToCart(Cart c, Product p, Integer quantity) throws CartException;

	public Cart removeProductFromCart(Cart c, Product p) throws CartException;

	public Cart updateProductQuantity(Cart c, Product p, Integer quantity) throws CartException;

	public Cart removeAllProduct(Cart c) throws CartException;

	public Cart viewAllProduct(Cart c) throws CartException;

}