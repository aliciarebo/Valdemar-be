package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.valdemarbe.model.entitybean.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
