package com.ecommerce.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}
