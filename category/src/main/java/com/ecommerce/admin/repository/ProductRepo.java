package com.ecommerce.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	
	List<Product> findAllByNameContaining(String title);
}
