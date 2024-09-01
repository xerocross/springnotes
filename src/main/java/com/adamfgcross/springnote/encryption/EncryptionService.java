package com.adamfgcross.springnote.encryption;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import com.adamfgcross.springnote.entities.Note;

@Service
public class EncryptionService {
	
	@Value("@{spring.secret-key}")
	private String secretKey;
	
	public String encryptData(String data, String password, String salt) {
		TextEncryptor encryptor = Encryptors.text(password, salt);
		return encryptor.encrypt(data);
	}
	
	public String decrypt(String encryptedData, String password, String salt) {
		TextEncryptor encryptor = Encryptors.text(password, salt);
		return encryptor.decrypt(encryptedData);
	}
	
	public String getDecryptedNoteData(Note note) {
		return null;
	}
	
	public void setEncryptedNoteData(Note note, String text) {
		
	}
	
}
