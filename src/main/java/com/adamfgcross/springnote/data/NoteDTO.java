package com.adamfgcross.springnote.data;

import java.util.ArrayList;
import java.util.List;

import com.adamfgcross.springnote.entities.Note;

public class NoteDTO {
	public NoteDTO (Long id, String user, String text)
	{
		this.id = id;
		this.user = user;
		this.text = text;
	}
	
	public NoteDTO (Note note) {
		this.id = note.getId();
		this.user = note.getUser().getUsername();
		this.text = note.getText();
		note.getKeywords().forEach(keyword -> {
			this.getKeywords().add(keyword.getKeyword());
		});
	}
	
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public String getUser() {
		return user;
	}
	public void setUsername(String user) {
		this.user = user;
	}
	private List<String> keywords = new ArrayList<>();
	private String user;
}
