package com.ecommerce.admin.product.services;

import java.io.IOException;
import java.util.List;

import com.ecommerce.admin.Dto.ProductDto;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto) throws IOException;
	
	public List<ProductDto> getAllProducts();
	
	List<ProductDto> getAllProductsByName(String name);

	boolean deleteProduct(Long id);
	}

