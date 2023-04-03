package com.apirest.valdemarbe.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("select g from Genre g join g.collections c where c.id=?1")
    public List<Genre> findByCollection(int idCollection);
}
