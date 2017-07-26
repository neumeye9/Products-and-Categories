package com.krista.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krista.products.models.Category;
import com.krista.products.models.Product;
import com.krista.products.repositories.CategoryRepository;

@Service
public class CategoryService{
	
	private CategoryRepository categoryRepository;
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	public List<Category> allCategories(){
		return(List<Category>) categoryRepository.findAll();
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	
	public Category findCategoryById(Long id) {
		return categoryRepository.findOne(id);
	}
	
	public void update(Category category){
		categoryRepository.save(category);
	}
}
