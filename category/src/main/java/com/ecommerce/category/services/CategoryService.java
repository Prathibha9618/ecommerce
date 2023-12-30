package com.ecommerce.category.services;


import com.ecommerce.category.Dto.CategoryDto;
import com.ecommerce.category.entity.Category;


public interface CategoryService {
	
	Category createCategory(CategoryDto categoryDto);

}
