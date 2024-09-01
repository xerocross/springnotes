package com.adamfgcross.springnote.service;

import org.springframework.stereotype.Service;

import com.adamfgcross.springnote.auth.JwtUtil;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
    	Collection<GrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, authorities));
        return jwtUtil.generateToken(authentication);
    }

	
}
