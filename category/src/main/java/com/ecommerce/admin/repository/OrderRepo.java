package com.ecommerce.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

}