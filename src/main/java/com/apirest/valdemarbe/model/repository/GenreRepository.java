package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.valdemarbe.model.entitybean.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
