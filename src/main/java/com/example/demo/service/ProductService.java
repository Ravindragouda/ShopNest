package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repositary.ProductRepositary;

@Service
public class ProductService {

	@Autowired
	ProductRepositary productRepositary;
	
	public void addProduct(Product product) {
		productRepositary.save(product);
	}

	public boolean productExists(String productName) {
		Product product = productRepositary.findByProductName(productName);
		if(product == null) {
			return false;
		}else {
			return true;
		}
		
	}

	public List<Product> fetchAllProducts() {
		return productRepositary.findAll();
	}

	public Product getProductById(int productId) {
        Optional<Product> productOptional = productRepositary.findById(productId);
        return productOptional.orElse(null);
    }
	public List<Product> searchItem(String keyword) {
	    return productRepositary.findByProductNameContainingIgnoreCase(keyword);
	}
	

	
	
	
}
