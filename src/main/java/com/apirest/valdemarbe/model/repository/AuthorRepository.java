package com.apirest.valdemarbe.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("select a from Author a join a.collections c where c.id=?1")
    public List<Author> findByCollection(int idCollection);

}
