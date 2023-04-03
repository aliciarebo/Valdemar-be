package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Author;
import com.apirest.valdemarbe.service.AuthorService;

@RestController
@RequestMapping("/rest/author")
public class AuthorRestController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return new ResponseEntity<List<Author>>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<List<Author>> findByCollection(@PathVariable("id") int id) {
        return new ResponseEntity<List<Author>>(authorService.findByCollection(id), HttpStatus.OK);
    }
}
