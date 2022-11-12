package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shopping.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
