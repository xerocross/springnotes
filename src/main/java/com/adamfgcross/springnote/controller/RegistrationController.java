package com.adamfgcross.springnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adamfgcross.springnote.domain.User;
import com.adamfgcross.springnote.dto.UserRegistrationInformation;
import com.adamfgcross.springnote.service.RegistrationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping
    public ResponseEntity<?> login(@RequestBody RegistrationRequest registrationRequest) {
        try {
        	UserRegistrationInformation userRegistrationInformation = new UserRegistrationInformation();
        	userRegistrationInformation.setUsername(registrationRequest.getUsername());
        	userRegistrationInformation.setPassword(registrationRequest.getPassword());
        	User user = registrationService.registerNewUser(userRegistrationInformation);
            return ResponseEntity.ok(new RegistrationResponse(user.getUsername()));
        } catch (Exception e) {
        	logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred.");
        }
    }
}

class RegistrationRequest {
	private String username;
	private String password;
	private String invitationToken;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInvitationToken() {
		return invitationToken;
	}
	public void setInvitationToken(String invitationToken) {
		this.invitationToken = invitationToken;
	}
}

class RegistrationResponse {
    private String username;

    public RegistrationResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}