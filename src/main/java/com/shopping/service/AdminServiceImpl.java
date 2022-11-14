package com.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.ProductException;
import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.repository.CategoryRepo;
import com.shopping.repository.ProductRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ProductRepo prodRepo;

	@Autowired
	private CategoryRepo cRepo;

	@Override
	public Product addProduct(Product product) throws ProductException {
		Optional<Product> opt = null;
		if (product.getProductId() != null)
			opt = prodRepo.findById(product.getProductId());

		if (opt != null && opt.isPresent()) {
			throw new ProductException("Product Already Registered!.......");
		} else {
			String categoryName = product.getCategory();
			Category existingCategory = cRepo.findByCategory(categoryName);
			if (existingCategory == null) {
				Category category = new Category();
				category.setCategory(categoryName);
				category.getProducts().add(product);
				category = cRepo.save(category);
				product.setCategorys(category);
			} else {
				existingCategory.getProducts().add(product);
				product.setCategorys(existingCategory);
			}
			Product savedProduct = prodRepo.save(product);

			return savedProduct;
		}
	}

	@Override
	public Product removeProduct(Integer pid) throws ProductException {
		Optional<Product> opt = prodRepo.findById(pid);
		if (!opt.isPresent())
			throw new ProductException("This Product doesn't exist");
		prodRepo.delete(opt.get());
		return opt.get();
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {

		Optional<Product> opt = prodRepo.findById(product.getProductId());

		if (!opt.isPresent())
			throw new ProductException("No Product exists with this information");

		Category cat = opt.get().getCategorys();
		product.setCategorys(cat);
		Product updatedProduct = prodRepo.save(product);
		if (updatedProduct == null)
			throw new ProductException("Product not updated");
		return updatedProduct;
	}

}
