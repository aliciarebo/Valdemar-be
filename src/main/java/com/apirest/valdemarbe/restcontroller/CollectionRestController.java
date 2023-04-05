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

import com.apirest.valdemarbe.model.entitybean.Author;
import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.model.entitybean.Genre;
import com.apirest.valdemarbe.service.AuthorService;
import com.apirest.valdemarbe.service.BookService;
import com.apirest.valdemarbe.service.CollectionService;
import com.apirest.valdemarbe.service.GenreService;

@RestController
@RequestMapping("/api/collections")
public class CollectionRestController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Collection>> findAll() {
        return new ResponseEntity<List<Collection>>(collectionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection> findOne(@PathVariable("id") String id) {
        return new ResponseEntity<Collection>(collectionService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCollection(@RequestBody Collection collection) {
        int result = collectionService.saveCollection(collection);
        if (result == 1) {
            return new ResponseEntity<Collection>(collection, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/{id}/authors")
    public ResponseEntity<?> addAuthor(@PathVariable("id") String id, @RequestBody Author author) {
        Author auth = authorService.findOne(author.getId());
        Collection collection = collectionService.findOne(id);

        if (auth == null) {
            return new ResponseEntity<String>("El autor no existe o es incorrecto", HttpStatus.BAD_REQUEST);
        }

        if (collection == null) {
            return new ResponseEntity<String>("La colección no existe o es incorrecta", HttpStatus.BAD_REQUEST);
        }

        if (collection.getAuthors().contains(auth)) {
            return new ResponseEntity<String>("El autor ya está presente en la colección", HttpStatus.BAD_REQUEST);
        }

        collection.getAuthors().add(auth);
        collectionService.saveCollection(collection);

        return new ResponseEntity<String>("Añadido", HttpStatus.OK);
    }

    @PostMapping("/{id}/genres")
    public ResponseEntity<?> addGenre(@PathVariable("id") String id, @RequestBody Genre genre) {
        Genre gen = genreService.findOne(genre.getId());
        Collection collection = collectionService.findOne(id);

        if (gen == null) {
            return new ResponseEntity<String>("El género no existe o es incorrecto", HttpStatus.BAD_REQUEST);
        }

        if (collection == null) {
            return new ResponseEntity<String>("La colección no existe o es incorrecta", HttpStatus.BAD_REQUEST);
        }

        if (collection.getGenres().contains(gen)) {
            return new ResponseEntity<String>("El autor ya está presente en la colección", HttpStatus.BAD_REQUEST);
        }

        collection.getGenres().add(gen);
        collectionService.saveCollection(collection);

        return new ResponseEntity<String>("Añadido", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String id) {

        Collection collection = collectionService.findOne(id);

        if (collection == null) {
            return new ResponseEntity<String>("Ese libro no existe", HttpStatus.NOT_FOUND);
        }

        collectionService.deleteCollection(id);
        return new ResponseEntity<String>("Libro borrado", HttpStatus.OK);
    }
}
