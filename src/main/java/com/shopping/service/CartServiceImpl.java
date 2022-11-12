package com.shopping.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.CartException;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.repository.CartDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Override
	public Cart addProductToCart(Cart c, Product p, Integer quantity) throws CartException {
		Optional<Cart> optional = cartDao.findById(c.getCartId());
		if (!optional.isPresent())
			throw new CartException("No Cart exits for adding this product");

		c.getProducts().put(p, quantity);
		Cart cart = cartDao.save(c);

		return cart;
	}

	@Override
	public Cart removeProductFromCart(Cart c, Product p) throws CartException {
		Optional<Cart> optional = cartDao.findById(c.getCartId());
		if (!optional.isPresent())
			throw new CartException("No Cart exits for remove this product");

		if (!c.getProducts().containsKey(p))
			throw new CartException("no similer product exits");

		Integer i = c.getProducts().remove(p);
		if (i < 0)
			throw new CartException("product is not deleted");

		Cart cart = cartDao.save(c);

		return cart;
	}

	@Override
	public Cart updateProductQuantity(Cart c, Product p, Integer quantity) throws CartException {
		Optional<Cart> optional = cartDao.findById(c.getCartId());
		if (!optional.isPresent())
			throw new CartException("No Cart exits for update the quantity");

		c.getProducts().put(p, quantity);

		Cart cart = cartDao.save(c);
		return cart;
	}

	@Override
	public Cart removeAllProduct(Cart c) throws CartException {
		Optional<Cart> optional = cartDao.findById(c.getCartId());
		if (!optional.isPresent())
			throw new CartException("No Cart exits for removing the products");
		c.setProducts(new HashMap<>());
		Cart cart = cartDao.save(c);

		return cart;
	}

	@Override
	public Map<Product, Integer> viewAllProduct(Cart c) throws CartException {
		Optional<Cart> optional = cartDao.findById(c.getCartId());
		if (!optional.isPresent())
			throw new CartException("No Cart exits");
		Map<Product, Integer> producList = optional.get().getProducts();
		return producList;
	}

}
