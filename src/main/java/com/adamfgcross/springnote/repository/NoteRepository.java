package com.adamfgcross.springnote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adamfgcross.springnote.domain.Note;
import com.adamfgcross.springnote.domain.User;

public interface NoteRepository extends JpaRepository<Note, Long> {

	@Query("SELECT n FROM Note n JOIN n.keywords k " + 
			"WHERE n.user = :user AND k.keyword IN :keywords " +
	           "GROUP BY n.id HAVING COUNT(DISTINCT k.id) = :size")
	List<Note> findNotesByAllKeywords(@Param("user") User user, @Param("keywords") List<String> keywords, @Param("size") long size);
}
