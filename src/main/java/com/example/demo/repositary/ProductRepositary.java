package com.example.demo.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Product;

public interface ProductRepositary extends JpaRepository<Product, Integer>{

	Product findByProductName(String productName);
	
	List<Product> findByProductNameContainingIgnoreCase(String keyword);

	
	
}
