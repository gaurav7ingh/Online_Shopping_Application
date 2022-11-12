package com.shopping.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.exception.ProductException;
import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.repository.CategoryRepo;
import com.shopping.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo prodRepo;

	@Autowired
	private CategoryRepo cRepo;

	@Override
	public List<Product> viewAllProducts() throws ProductException {

		List<Product> products = prodRepo.findAll();
		if (products.size() == 0) {
			throw new ProductException("No Product are there");
		} else {
			return products;
		}
	}

	

	
	@Override
	public Product viewProduct(Integer id) throws ProductException {

		Optional<Product> opt = prodRepo.findById(id);
		if (opt.isPresent()) {
			Product viewProduct = opt.get();
			return viewProduct;
		} else {
			throw new ProductException("Product not available!......");
		}
	}

	@Override
	public List<Product> viewProductByCategory(String cname) throws ProductException {

		Category category = cRepo.findByCategory(cname);

		if (category == null)
			throw new ProductException("No Product found in this category");
		List<Product> products = category.getProducts();

		if (products.isEmpty())
			throw new ProductException("No Product found in this category");

		return products;

	}

	

}