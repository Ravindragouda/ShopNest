package com.example.demo.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        model.addAttribute("cart", cart);

        double totalPrice = 0.0;
        if (cart != null) {
            for (Product product : cart) {
                totalPrice += product.getPrice();
            }
        }
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }
    @GetMapping("/orders")
    public String viewOrders(Model model, HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        model.addAttribute("cart", cart);


        return "orders";
    }


    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") int productId, HttpSession session) {
        Product product = productService.getProductById(productId);
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(product);
        session.setAttribute("cart", cart);
        return "redirect:/customerPage"; 
    }

    @PostMapping("/deleteItem")
    public String deleteItemFromCart(@RequestParam("deleteProduct") int id, HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart != null) {
            for (Product product : cart) {
                if (product.getId() == id) {
                    cart.remove(product);
                    break;
                }
            }
        }
        return "redirect:/cart";
    }
    
    @GetMapping("/cart/totalPrice")
    @ResponseBody
    public double getTotalPrice(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        double totalPrice = 0.0;
        if (cart != null) {
            for (Product product : cart) {
                totalPrice += product.getPrice();
            }
        }
        totalPrice *= 100;
        return totalPrice;
    }


	

}
