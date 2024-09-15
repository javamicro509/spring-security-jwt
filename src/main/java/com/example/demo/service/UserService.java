package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	public Users register(Users user) {
		return userRepo.save(user);
	}

	public String verify(Users user) {
		// TODO Auto-generated method stub
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername());
		
		return "User is not authenticated";
	}

}
