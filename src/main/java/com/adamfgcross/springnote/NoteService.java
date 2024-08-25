package com.adamfgcross.springnote;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.adamfgcross.springnote.data.NoteRepository;
import com.adamfgcross.springnote.entities.Keyword;
import com.adamfgcross.springnote.entities.Note;
import com.adamfgcross.springnote.entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public Note createNote(User user, NoteInput noteInput) {
		Note note = new Note();
		note.setUser(user);
		note.setText(noteInput.getText());
		return noteRepository.save(note);
	}
	
	public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

	private Set<Keyword> parseKeywords(String textContent) {
		String hashtagPattern = "#\\w+";
		Pattern pattern = Pattern.compile(hashtagPattern);
		Set<Keyword> keywords = new HashSet<>();
		Matcher matcher = pattern.matcher(textContent);
		while (matcher.find()) {
			String keywordText = matcher.group();
			Keyword keyword = new Keyword(keywordText);
			keywords.add(keyword);
        }
		return keywords;
	}
}
