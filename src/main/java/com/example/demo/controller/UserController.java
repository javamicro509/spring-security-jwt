package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
	
	
	//register user
	//localhost:8080/register
	//{"id":1,"username":"vikash","password":"vikash"}
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println(user);
		return userService.register(user);
	}
	
	//login user
	//localhost:8080/login
	//{"username":"vikash","password":"vikash"}
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		
		return userService.verify(user);
	}

	
}
