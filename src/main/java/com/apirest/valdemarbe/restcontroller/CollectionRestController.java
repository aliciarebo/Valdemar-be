package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.service.CollectionService;

@RestController
@RequestMapping("/api/collection")
public class CollectionRestController {
    @Autowired
    CollectionService collectionService;

    @GetMapping
    public ResponseEntity<List<Collection>> findAll() {
        return new ResponseEntity<List<Collection>>(collectionService.findAll(), HttpStatus.OK);
    }
}
