package com.adamfgcross.springnote.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adamfgcross.springnote.domain.Keyword;
import com.adamfgcross.springnote.domain.Note;
import com.adamfgcross.springnote.domain.User;
import com.adamfgcross.springnote.dto.NoteInput;
import com.adamfgcross.springnote.repository.KeywordRepository;
import com.adamfgcross.springnote.repository.NoteRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NoteService {
	
	private static final Logger logger = LoggerFactory.getLogger(NoteService.class);
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private KeywordRepository keywordRepository;
	
	public Note createSecureNote(User user, NoteInput noteInput) {
		return null;
	}
	
	
	public Note createNote(User user, NoteInput noteInput) {
		Note note = new Note();
		note.setUser(user);
		note.setText(noteInput.getText());
		noteRepository.save(note);
		String text = noteInput.getText();
		logger.info("note text: " + text);
		Set<Keyword> keywords = parseKeywords(text);
		
		keywords.forEach(keyword -> {
			logger.info("found keyword: " + keyword.getKeyword());
			note.getKeywords().add(keyword);
			keyword.getNotes().add(note);
			keywordRepository.save(keyword);
		});

		return noteRepository.save(note);
	}
	
	public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

	public List<Note> getNotesByKeyword(User user, List<String> keywords) {
		return noteRepository.findNotesByAllKeywords(user, keywords, keywords.size());
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
