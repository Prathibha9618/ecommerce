package com.ecommerce.admin.Dto;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.entity.Category;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ProductDto {
	
	private Long id;
	
	private String name;
	
	private Long price;
	
	private String description;
	
	private byte[] byteImg;
	
	private Long categoryId;
	
	private String categoryName;
	
	private MultipartFile img;
	
}
