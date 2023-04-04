package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.config.JwtTokenUtil;
import com.apirest.valdemarbe.model.entitybean.Book;
import com.apirest.valdemarbe.model.entitybean.Wishlist;
import com.apirest.valdemarbe.service.BookService;
import com.apirest.valdemarbe.service.WishlistService;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistRestController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    BookService bookService;

    @GetMapping
    ResponseEntity<?> findWishlist(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtTokenUtil.getEmailFromToken(token);
        Wishlist wishlist = wishlistService.findByUser(email);
        return new ResponseEntity<List<Book>>(bookService.booksOfWishlist(wishlist.getId()), HttpStatus.OK);
    }
}
