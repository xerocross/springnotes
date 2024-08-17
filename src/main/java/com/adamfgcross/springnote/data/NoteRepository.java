package com.adamfgcross.springnote.data;

import com.adamfgcross.springnote.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

	
}
