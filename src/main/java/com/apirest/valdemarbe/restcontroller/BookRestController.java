package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Book;
import com.apirest.valdemarbe.service.AuthorService;
import com.apirest.valdemarbe.service.BookService;
import com.apirest.valdemarbe.service.CollectionService;

@RestController
@RequestMapping("/rest/book")
public class BookRestController {
    @Autowired
    BookService bookService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> findByAuthor(@PathVariable("id") int id) {
        if (authorService.findOne(id) == null) {
            return new ResponseEntity<String>("No existe ese autor", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(bookService.booksByAuthor(id), HttpStatus.OK);
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<?> findByCollection(@PathVariable("id") int id) {
        if (collectionService.findOne(id) == null) {
            return new ResponseEntity<String>("No existe esa colección", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(bookService.booksByCollection(id), HttpStatus.OK);
    }

    @GetMapping("/wishlist/{id}")
    public ResponseEntity<?> findByWishlist(@PathVariable("id") int id) {
        return new ResponseEntity<List<Book>>(bookService.booksOfWishlist(id), HttpStatus.OK);
    }

}
