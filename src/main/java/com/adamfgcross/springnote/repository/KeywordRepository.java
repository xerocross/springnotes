package com.adamfgcross.springnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamfgcross.springnote.domain.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

}
