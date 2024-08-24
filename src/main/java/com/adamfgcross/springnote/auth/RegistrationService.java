package com.adamfgcross.springnote.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adamfgcross.springnote.UserRegistrationInformation;
import com.adamfgcross.springnote.data.UserRepository;
import com.adamfgcross.springnote.entities.User;

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