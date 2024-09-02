package com.adamfgcross.springnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamfgcross.springnote.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
