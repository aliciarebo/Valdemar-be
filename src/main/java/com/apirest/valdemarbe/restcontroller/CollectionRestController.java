package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.service.BookService;
import com.apirest.valdemarbe.service.CollectionService;

@RestController
@RequestMapping("/api/collections")
public class CollectionRestController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Collection>> findAll() {
        return new ResponseEntity<List<Collection>>(collectionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCollection(@RequestBody Collection collection) {
        int result = collectionService.saveCollection(collection);
        if (result == 1) {
            return new ResponseEntity<Collection>(collection, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
