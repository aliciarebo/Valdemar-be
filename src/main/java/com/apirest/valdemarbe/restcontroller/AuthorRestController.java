package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Author;
import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.service.AuthorService;
import com.apirest.valdemarbe.service.CollectionService;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    @Autowired
    AuthorService authorService;

    @Autowired
    CollectionService collectionService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return new ResponseEntity<List<Author>>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findOne(@PathVariable("id") String id) {
        return new ResponseEntity<Author>(authorService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/collections/{id}")
    public ResponseEntity<?> findByCollection(@PathVariable("id") String id) {
        Collection collection = collectionService.findOne(id);
        if (collection == null) {
            return new ResponseEntity<String>("No existe esa colección", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Author>>(authorService.findByCollection(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Author author) {
        int result = authorService.saveAuthor(author);
        if (result == 1) {
            return new ResponseEntity<Author>(author, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
