package com.adamfgcross.springnote;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import com.adamfgcross.springnote.entities.Note;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
	
	@Autowired
	private NoteService noteService;

	@PostMapping
	public ResponseEntity<Note> createNote(@RequestBody Note note) {
		Note createdNote = noteService.createNote(note);
		return ResponseEntity.ok(createdNote);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
		Optional<Note> note = noteService.getNoteById(id);
		return note.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
