package com.ecommerce.admin.category.services;


import java.util.List;

import com.ecommerce.admin.Dto.CategoryDto;
import com.ecommerce.admin.entity.Category;


public interface CategoryService {
	
	Category createCategory(CategoryDto categoryDto);

	List<Category> getAllCategory();
}
