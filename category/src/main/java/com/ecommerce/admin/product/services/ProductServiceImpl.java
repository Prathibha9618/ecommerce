package com.ecommerce.admin.product.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.admin.Dto.ProductDto;
import com.ecommerce.admin.entity.Category;
import com.ecommerce.admin.entity.Product;
import com.ecommerce.admin.repository.CategoryRepo;
import com.ecommerce.admin.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	
	private final ProductRepo productRepo;
	
	
	private final CategoryRepo categoryRepo;
	
	public ProductDto addProduct(ProductDto productDto) throws IOException {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImg(productDto.getImg().getBytes());
		
		Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
		
		product.setCategory(category);
		return productRepo.save(product).getDto();
		
	}
	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepo.findAll();
		
		return products.stream().map(Product::getDto).collect(Collectors.toList());
		
	}
	
	public List<ProductDto> getAllProductsByName(String name){
		List<Product> products = productRepo.findAllByNameContaining(name);
		
		return products.stream().map(Product::getDto).collect(Collectors.toList());
		
	}
	public boolean deleteProduct(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			productRepo.deleteById(id);
			return true;
		}
		return false;
		
	}
	
}
