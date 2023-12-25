package com.ecommerce.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.category.Dto.CategoryDto;
import com.ecommerce.category.entity.Category;
import com.ecommerce.category.services.CategoryService;


@RestController
@RequestMapping("/admin")
public class CategoryController {
	
	
	private final CategoryService categoryService = null;
	
	@PostMapping("/category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
		Category category = categoryService.createCategory(categoryDto);
		System.out.println(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
		
	}

}
