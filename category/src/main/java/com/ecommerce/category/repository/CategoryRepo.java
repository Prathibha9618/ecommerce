package com.ecommerce.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.category.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}
