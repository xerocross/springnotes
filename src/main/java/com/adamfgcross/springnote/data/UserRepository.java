package com.adamfgcross.springnote.data;

import com.adamfgcross.springnote.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {
	Optional<User> findByUsername(String username);
}
