package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;



@Controller
public class ProductController{

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product) {
    	
		boolean productExists = productService.productExists(product.getProductName());
		
		if(productExists == false) {
			product.setImage(product.getImage());
			productService.addProduct(product);
		}else {
			System.out.println("product already exists");
		}
		return "adminPage";
    }
       
    @GetMapping("displayProduct")
	public String viewAllProducts(Model model) {
		List<Product> productList = productService.fetchAllProducts();
		model.addAttribute("products", productList);
		return "displayProduct";
	}
    @GetMapping("/customerPage")
    public String viewAllProductsInCustomer(Model model) {
    	List<Product> productList = productService.fetchAllProducts();
    	model.addAttribute("products", productList);
    	return "customerPage";
    }
    @GetMapping("/searchItem")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> productList = productService.searchItem(keyword);
        model.addAttribute("products", productList);
        return "customerPage";
    }
    
    
  

    
   
}
