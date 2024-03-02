package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositary.UserRepositary;

@Service
public class UserService {

	@Autowired
	UserRepositary repo;

	public String addUser(User user) {
		repo.save(user);
		return "user added";
		}
	
	public boolean emailExists(String email) {
		return repo.findByEmail(email) != null;
	}

	public boolean validateUser(String email, String password) {
		
		User user = repo.findByEmail(email);
		
		if(user !=null) {
			String db_pass = user.getPassword();
			if(password != null && password.equals(db_pass)) {
				return true;
			}
		}
		return false;
	}

	public String getRole(String email) {
		User role = repo.findByEmail(email);
		return role.getRole();
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	
}
