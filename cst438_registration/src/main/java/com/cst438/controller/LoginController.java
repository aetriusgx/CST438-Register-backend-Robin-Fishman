package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.UserRepository;
import com.cst438.domain.User;
import com.cst438.domain.UserDTO;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/login")
	public UserDTO attemptLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find a user by that email.");
		}
		
		return new UserDTO(user.getEmail(), user.getRole());
	}
}
