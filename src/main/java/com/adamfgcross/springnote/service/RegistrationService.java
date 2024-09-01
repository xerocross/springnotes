package com.adamfgcross.springnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adamfgcross.springnote.domain.User;
import com.adamfgcross.springnote.dto.UserRegistrationInformation;
import com.adamfgcross.springnote.repository.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(UserRegistrationInformation userRegistrationInformation) {
		User user = new User();
		user.setUsername(userRegistrationInformation.getUsername());
		String encryptedPassword = passwordEncoder.encode(userRegistrationInformation.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		return user;
	}
	
}