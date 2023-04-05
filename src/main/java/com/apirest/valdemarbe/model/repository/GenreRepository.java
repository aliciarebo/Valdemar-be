package com.apirest.valdemarbe.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("select c.genres FROM Collection c WHERE c.id = ?1")
    public List<Genre> findByCollection(String id);

    @Query("select g from Genre g where g.id =?1")
    public Genre findOne(String id);
}
