package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
