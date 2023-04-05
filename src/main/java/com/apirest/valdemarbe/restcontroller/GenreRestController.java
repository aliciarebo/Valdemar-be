package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.model.entitybean.Genre;
import com.apirest.valdemarbe.service.CollectionService;
import com.apirest.valdemarbe.service.GenreService;

@RestController
@RequestMapping("/api/genres")
public class GenreRestController {

    @Autowired
    GenreService genreService;

    @Autowired
    CollectionService collectionService;

    @GetMapping
    public ResponseEntity<List<Genre>> findAll() {
        return new ResponseEntity<List<Genre>>(genreService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findOne(@PathVariable("id") String id) {
        return new ResponseEntity<Genre>(genreService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/collections/{id}")
    public ResponseEntity<?> findByCollection(@PathVariable("id") String id) {
        Collection collection = collectionService.findOne(id);
        if (collection == null) {
            return new ResponseEntity<String>("No existe esa colección", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Genre>>(genreService.findByCollection(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        int result = genreService.saveGenre(genre);
        if (result == 1) {
            return new ResponseEntity<Genre>(genre, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable("id") String id) {

        Genre genre = genreService.findOne(id);

        if (genre == null) {
            return new ResponseEntity<String>("El genero no existe", HttpStatus.NOT_FOUND);
        }

        genreService.deleteGenre(genre);
        return new ResponseEntity<String>("Género borrado", HttpStatus.OK);
    }
}
