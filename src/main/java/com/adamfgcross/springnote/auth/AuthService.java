package com.adamfgcross.springnote.auth;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, 
    		JwtUtil jwtUtil
    		) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(authentication);
    }

	
}
