package com.adamfgcross.springnote.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;


@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	@Column(nullable = true)
	private String text;
	
	@Lob
	private byte[] encryptedData;
	
	public byte[] getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(byte[] encryptedData) {
		this.encryptedData = encryptedData;
	}
	
	private String encryptionSalt;
	

	public String getEncryptionSalt() {
		return encryptionSalt;
	}

	public void setEncryptionSalt(String encryptionSalt) {
		this.encryptionSalt = encryptionSalt;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@ManyToMany
	@JoinTable(
		name = "note_keyword",
		joinColumns = @JoinColumn(name = "note_id"),
		inverseJoinColumns = @JoinColumn(name = "keyword_id")
	)
	private Set<Keyword> keywords = new HashSet<>();

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}
	
}
