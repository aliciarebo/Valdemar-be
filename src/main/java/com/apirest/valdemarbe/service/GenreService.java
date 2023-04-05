package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.Genre;

public interface GenreService {

    List<Genre> findAll();

    Genre findOne(String id);

    List<Genre> findByCollection(String id);

    int saveGenre(Genre genre);
}
