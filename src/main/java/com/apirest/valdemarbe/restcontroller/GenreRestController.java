package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/collections/{id}")
    public ResponseEntity<?> findByCollection(@PathVariable("id") String id) {
        Collection collection = collectionService.findOne(id);
        if (collection == null) {
            return new ResponseEntity<String>("No existe esa colección", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Genre>>(genreService.findByCollection(id), HttpStatus.OK);
    }
}
