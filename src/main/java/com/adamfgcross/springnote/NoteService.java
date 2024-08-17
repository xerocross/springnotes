package com.adamfgcross.springnote;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.adamfgcross.springnote.data.NoteRepository;
import com.adamfgcross.springnote.entities.Note;

import java.util.Optional;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public Note createNote(Note note) {
		return noteRepository.save(note);
	}
	
	public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

}
