package com.apirest.valdemarbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.Genre;
import com.apirest.valdemarbe.model.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepo;

    @Override
    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    @Override
    public List<Genre> findByCollection(String id) {
        return genreRepo.findByCollection(id);
    }

    @Override
    public Genre findOne(int idGenre) {
        return genreRepo.findById(idGenre).orElse(null);
    }

    @Override
    public int saveGenre(Genre genre) {
        try {
            genreRepo.save(genre);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

}
