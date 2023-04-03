package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Genre;
import com.apirest.valdemarbe.service.GenreService;

@RestController
@RequestMapping("/rest/genre")
public class GenreRestController {

    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> findAll() {
        return new ResponseEntity<List<Genre>>(genreService.findAll(), HttpStatus.OK);
    }

}
