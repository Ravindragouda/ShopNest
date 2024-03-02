package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user, Model model) {

		try {
			boolean userStatus = userService.emailExists(user.getEmail());
			
			if(!userStatus) {
				userService.addUser(user);
				return "login";
			}else {
				model.addAttribute("error", "User already exists");
				return "register";
			}
			
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/validate")
	public String validateUser(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {

		if (userService.validateUser(email, password) == true) {
			String role = userService.getRole(email);
			if (role.equals("admin")) {
				return "adminPage";
			} else {
				return "redirect:/customerPage";
			}
		} else {
			return "login";
		}
	}
	
	@GetMapping("/displayUsers")
	public String displayUsers(Model model) {
		
	List<User> UsersList=userService.getAllUsers();
		model.addAttribute("users", UsersList);
		return "displayUsers";
	}
	
	
	
	
	
}
