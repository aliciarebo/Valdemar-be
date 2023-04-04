package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Book;
import com.apirest.valdemarbe.model.entitybean.Wishlist;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // obtener el nombre de usuario del usuario autenticado
        Wishlist wishlist = wishlistService.findOne(id);
        String email = authentication.getName();

        if (wishlist == null) {
            return new ResponseEntity<String>("No existe esta lista", HttpStatus.NOT_FOUND);
        }

        // verificar si el usuario autenticado es el mismo que creó la lista de deseos
        if (!wishlist.getUser().getEmail().equals(email)) {
            return new ResponseEntity<String>("No estás autorizado para acceder a esta lista", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<List<Book>>(bookService.booksOfWishlist(id), HttpStatus.OK);
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<?> findByGenre(@PathVariable("id") int id) {
        if (genreService.findOne(id) == null) {
            return new ResponseEntity<String>("No existe ese género", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(bookService.booksByGenre(id), HttpStatus.OK);
    }

}
