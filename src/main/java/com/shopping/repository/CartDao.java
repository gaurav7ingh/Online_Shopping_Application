package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

}
