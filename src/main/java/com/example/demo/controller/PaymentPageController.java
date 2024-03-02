package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentPageController {
	@Autowired
	ProductService productService;
	@GetMapping("/paymentPage")
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

        return "paymentPage";
    }
	
	
	@GetMapping("/paymentPage/totalPrice")
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
