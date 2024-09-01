package com.adamfgcross.springnote;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(NotesController.class);
	
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
	
	@PostMapping("/secure")
	public ResponseEntity<NoteDTO> createSecureNote(@AuthenticationPrincipal CustomUserDetails customUserDetails, 
													@RequestBody NoteInput noteInput) {
		User user = customUserDetails.getUser();
		Note note = noteService.createNote(user, noteInput);
		NoteDTO noteDTO = new NoteDTO(note);
		return ResponseEntity.ok(noteDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getNoteById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
									@PathVariable Long id) {
		User user = customUserDetails.getUser();
		Optional<Note> noteOptional = noteService.getNoteById(id);
		return noteOptional.map(note -> {
			if (note.getUser().equals(user)) {
				return ResponseEntity.ok(new NoteDTO(note));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to access this note.");
			}
		})
		.orElseGet(() -> 
			ResponseEntity.notFound().build()
		);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<NoteDTO>> searchNotes(@AuthenticationPrincipal CustomUserDetails customUserDetails, 
    												@RequestParam("keywords") List<String> keywords) {
		
		User user = customUserDetails.getUser();
		List<Note> notes = noteService.getNotesByKeyword(user, keywords);
        List<NoteDTO> noteDTOs = notes.stream()
        		.map(NoteDTO::new)
        		.toList();
        return new ResponseEntity<>(noteDTOs, HttpStatus.OK);
    }
}

class NoteInput {
    private String text;
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
