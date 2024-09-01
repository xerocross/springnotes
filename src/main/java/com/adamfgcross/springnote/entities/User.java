package com.adamfgcross.springnote.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;

@Entity
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private boolean enabled;
	
	private String encryptionSalt;
	
	public String getEncryptionSalt() {
		return encryptionSalt;
	}

	public void setEncryptionSalt(String encryptionSalt) {
		this.encryptionSalt = encryptionSalt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    	return authorities;
	}
	
	@OneToMany(mappedBy = "user")
	private List<Note> notes = new ArrayList<>();

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	@OneToMany(mappedBy = "user")
	private List<Keyword> keywords = new ArrayList<>();

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if they are the same instance
        if (o == null || getClass() != o.getClass()) return false; // Check if o is null or not of the same class
        User user = (User) o; // Cast o to User and compare relevant fields
        return Objects.equals(id, user.id) &&
               Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username); // Generate hash code based on relevant fields
    }
	
}
