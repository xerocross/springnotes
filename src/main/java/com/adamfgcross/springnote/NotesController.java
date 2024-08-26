package com.adamfgcross.springnote;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.adamfgcross.springnote.auth.CustomUserDetails;
import com.adamfgcross.springnote.entities.Note;
import com.adamfgcross.springnote.entities.User;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
	
	@Autowired
	private NoteService noteService;

	@PostMapping
	public ResponseEntity<CreatedNoteResponse> createNote(@AuthenticationPrincipal CustomUserDetails customUserDetails, 
			@RequestBody NoteInput noteInput) {
		User user = customUserDetails.getUser();
		Note createdNote = noteService.createNote(user, noteInput);
		CreatedNoteResponse createdNoteResponse = new CreatedNoteResponse(user.getUsername(), createdNote.getText());
		createdNote.getKeywords().forEach(keyword -> {
			createdNoteResponse.getKeywords().add(keyword.getKeyword());
		});
		
		return ResponseEntity.ok(createdNoteResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
		Optional<Note> note = noteService.getNoteById(id);
		return note.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}

class NoteInput {
    private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

class CreatedNoteResponse {
	
	public CreatedNoteResponse (String username, String text)
	{
		this.username = username;
		this.text = text;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private List<String> keywords = new ArrayList<>();
	private String username;
	
}
