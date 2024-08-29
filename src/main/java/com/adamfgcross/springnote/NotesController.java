package com.adamfgcross.springnote;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

import com.adamfgcross.springnote.auth.CustomUserDetails;
import com.adamfgcross.springnote.data.NoteDTO;
import com.adamfgcross.springnote.entities.Note;
import com.adamfgcross.springnote.entities.User;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
	
	@Autowired
	private NoteService noteService;

	@PostMapping
	public ResponseEntity<NoteDTO> createNote(@AuthenticationPrincipal CustomUserDetails customUserDetails, 
			@RequestBody NoteInput noteInput) {
		User user = customUserDetails.getUser();
		Note note = noteService.createNote(user, noteInput);
		NoteDTO noteDTO = new NoteDTO(note);
		return ResponseEntity.ok(noteDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {
		Optional<Note> note = noteService.getNoteById(id);
		return note.map(NoteDTO::new)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<NoteDTO>> searchNotes(@RequestParam("keywords") List<String> keywords) {
		
        List<Note> notes = noteService.getNotesByKeyword(keywords);
        List<NoteDTO> noteDTOs = notes.stream()
        		.map(NoteDTO::new)
        		.toList();
        return new ResponseEntity<>(noteDTOs, HttpStatus.OK);
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
