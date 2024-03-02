package com.example.demo.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String productName;
	private String description;
	private double price;
    private String image;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String productName, String description, double price, String image) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", image=" + image + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
  
    

}
