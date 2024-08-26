package com.adamfgcross.springnote.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamfgcross.springnote.entities.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

}
