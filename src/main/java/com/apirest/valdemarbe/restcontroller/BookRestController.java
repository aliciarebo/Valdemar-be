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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.valdemarbe.model.entitybean.Book;
import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.service.AuthorService;
import com.apirest.valdemarbe.service.BookService;
import com.apirest.valdemarbe.service.CollectionService;
import com.apirest.valdemarbe.service.GenreService;
import com.apirest.valdemarbe.service.WishlistService;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    @Autowired
    BookService bookService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    AuthorService authorService;

    @Autowired
    WishlistService wishlistService;

    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<?> findByTitle(@RequestParam(required = true) String title) {
        List<Book> books = bookService.findByTitleContaining(title);
        if (books.isEmpty()) {
            return new ResponseEntity<String>("No existen libros con ese título", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/id/{isbn}")
    public ResponseEntity<?> findOne(@PathVariable String isbn) {
        Book book = bookService.findOne(isbn);
        if (book == null) {
            return new ResponseEntity<String>("Libro no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String getFilteredBooks(@PathVariable("id") String id, @RequestParam String[] genres,
            @RequestParam String[] authors) {
        List<Book> books = bookService.filteredBooks(id, genres, authors);
        System.out.println(books);
        return "holiiiii :)";
    }

    @GetMapping("/collections/{id}")
    public ResponseEntity<?> findByCollection(@PathVariable("id") String id) {
        Collection collection = collectionService.findOne(id);
        if (collection == null) {
            return new ResponseEntity<String>("No existe esa colección", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(bookService.booksByCollection(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        int result = bookService.saveBook(book);
        if (result == 1) {
            return new ResponseEntity<Book>(book, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
