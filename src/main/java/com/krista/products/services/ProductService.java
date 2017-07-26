package com.krista.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krista.products.models.Category;
import com.krista.products.models.Product;
import com.krista.products.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> allProducts(){
		return(List<Product>) productRepository.findAll();
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public Product findProductById(Long id) {
		return productRepository.findOne(id);
	}

}
