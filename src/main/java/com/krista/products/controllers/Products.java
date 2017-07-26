package com.krista.products.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krista.products.models.Category;
import com.krista.products.models.Product;
import com.krista.products.services.CategoryService;
import com.krista.products.services.ProductService;


@Controller
public class Products {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
	public Products(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/product")
	public String newProduct(@ModelAttribute("product") Product product) {

		return "newProduct";
	}
	
	@RequestMapping("/product/new")
	public String newProduct(@ModelAttribute("product") Product product, Model model) {
		List<Category> categories = categoryService.allCategories();
		model.addAttribute("products", productService.allProducts());
		model.addAttribute("categories", categories);
		return "newProduct";
	}
	
	@PostMapping("/product/new")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes flash, Model model) {
		if(result.hasErrors()) {
			flash.addAttribute("errors", result.getAllErrors());
			return "newProduct";
		}
		else {
			System.out.println(product);
			productService.addProduct(product);
			return "redirect:/product/" + product.getId();
		}
	}
	
	@RequestMapping("/product/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		model.addAttribute("product", productService.findProductById(id));
		model.addAttribute("categories", categoryService.allCategories());
		List<Category> categories = product.getCategories();
		List<Category> allCats = categoryService.allCategories();
		ArrayList<Category> finalList = new ArrayList<>();

		for(int i = 0; i < allCats.size(); i ++) {
			int count = 0;
			for(Category j : categories) {
				if(j.getName().equals(allCats.get(i).getName())) {
					i++;
					break;
				}
				count++;
			}
			if(count == 0 || count == categories.size()) {
				finalList.add(allCats.get(i));
			}
		}
		model.addAttribute("allCats", finalList);
		
		System.out.println(allCats);
		return "showProduct";
	}
	
	@PostMapping("/product/{productId}")
	public String add(@PathVariable("productId") Long productId, @RequestParam("category") Long categoryId, Model model) {
		Product product = productService.findProductById(productId);
		Category category = categoryService.findCategoryById(categoryId);
		List<Category> categories = product.getCategories();
		
		
		
		categories.add(category);
		product.setCategories(categories);
		productService.addProduct(product);
		model.addAttribute("proId", product.getId());
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		
		return "redirect:/product/" + product.getId();
			
	}
	
	

	
}
