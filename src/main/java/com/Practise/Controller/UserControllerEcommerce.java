package com.Practise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Practise.Service.CategoryService;
import com.Practise.Service.ProductService;

@Controller
public class UserControllerEcommerce {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/user/shop")
	public String getShopping(Model model) {
		model.addAttribute("prodcts", productService.getAllProduct());
		model.addAttribute("categries", categoryService.getAllCategories());
		return "shop"; 
	}
	
	@GetMapping("/user/shop/{catId}")
	public String getShopping(Model model, @PathVariable Long catId) {
		model.addAttribute("prodcts", productService.getProductByCategoryId(catId));
		model.addAttribute("categries", categoryService.getAllCategories());
		
		System.out.println(productService.getProductByCategoryId(catId));
		
		return "shop"; 
	} 
	
	@GetMapping("/user/shop/viewproduct/{productId}")
	public String getViewProduct(Model model, @PathVariable Long productId) {
		model.addAttribute("prodcts", productService.getProductById(productId).get());
		
		System.out.println(productService.getProductById(productId));
		
		return "viewProduct"; 
	}
	
}
