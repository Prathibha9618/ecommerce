package com.ecommerce.category.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.category.Dto.CategoryDto;
import com.ecommerce.category.entity.Category;
import com.ecommerce.category.repository.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorySeviceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category createCategory(CategoryDto categoryDto) {
		
		System.out.println("catdto"+categoryDto);
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
        System.out.println("category service"+category);
		return categoryRepo.save(category);
		
	}
	
	

}
