package com.example.demo.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface UserRepositary extends JpaRepository<User, Integer>{

	User findByEmail(String email);

}
