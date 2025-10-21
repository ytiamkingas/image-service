package com.image.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.image.service.dto.LoginRequest;
import com.image.service.dto.RegisterRequest;
import com.image.service.service.AuthService;

@RestController
@RequestMapping
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		String token = authService.register(request);
		return ResponseEntity.ok().body("Registration successful. JWT: " + token);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		String token = authService.login(request);
		return ResponseEntity.ok().body("Login successful. JWT: " + token);
	}
}