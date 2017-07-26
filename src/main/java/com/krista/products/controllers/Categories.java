package com.krista.products.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/category")
public class Categories {
	
	private final CategoryService categoryService;
	private final ProductService productService;
	
	public Categories(CategoryService categoryService, ProductService productService) {
		this.categoryService = categoryService;
		this.productService = productService;
	}
	
	
	@RequestMapping("")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "newCategory";
	}
	
	@RequestMapping("/new")
	public String newCategory(@ModelAttribute("category") Category category, Model model) {
		List<Product> products = productService.allProducts();
		model.addAttribute("categories", categoryService.allCategories());
		model.addAttribute("products", products);
		return "newCategory";
	}
	
	@PostMapping("/new")
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes flash, Model model, HttpSession session) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "newCategory";
		}
		else {
			System.out.println(category);
			categoryService.addCategory(category);
			System.out.println(category.getId());
			return "redirect:/category/" + category.getId();
		}
	}
		
	@RequestMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model){
		Category category = categoryService.findCategoryById(id);
		model.addAttribute("category",categoryService.findCategoryById(id));
		model.addAttribute("products",productService.allProducts());
		List<Product> products = category.getProducts();
		List<Product> allProducts = productService.allProducts();
		ArrayList<Product> finalList = new ArrayList<>();
		
		for(int i = 0; i < allProducts.size(); i ++) {
			int count = 0;
			for(Product j : products) {
				if(j.getName().equals(allProducts.get(i).getName())) {
					i++;
					break;
				}
				count++;
			}
			if(count == 0 || count == products.size()) {
				finalList.add(allProducts.get(i));
			}
		}
		model.addAttribute("allProducts", finalList);
		
		return "showCategory";
	}
	
	@PostMapping("/{categoryId}")
	public String add(@PathVariable("categoryId") long categoryId,@RequestParam("product") long productId, Model model){
		Category category = categoryService.findCategoryById(categoryId);
		Product product   = productService.findProductById(productId);
		
		List<Product> products = category.getProducts();
		products.add(product);
		category.setProducts(products);
		categoryService.addCategory(category);
		model.addAttribute("catId",category.getId());
	
		model.addAttribute("product", product);
		model.addAttribute("category", category);
		
		return "redirect:/category/" + category.getId();
	}
	

}
