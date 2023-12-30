package com.ecommerce.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.admin.Dto.CategoryDto;
import com.ecommerce.admin.category.services.CategoryService;
import com.ecommerce.admin.entity.Category;


@RestController
@RequestMapping("api/admin")
public class CategoryController {
	
	@Autowired
	private  CategoryService categoryService;
	
	@PostMapping("/category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
		Category category = categoryService.createCategory(categoryDto);
		System.out.println(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
		
	}
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategory(){
		System.out.println(categoryService.getAllCategory());
		return ResponseEntity.ok(categoryService.getAllCategory());
	}

}
