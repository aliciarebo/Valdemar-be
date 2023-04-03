package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.Author;

public interface AuthorService {
    List<Author> findAll();

    Author findOne(int idAuthor);

    List<Author> findByCollection(int idCollection);
}
