package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.Book;

public interface BookService {
    List<Book> findAll();

    List<Book> booksByAuthor(int idAuthor);

    List<Book> booksByCollection(int idCollection);

    List<Book> booksOfWishlist(int idWishlist);

    List<Book> findByTitleContaining(String title);

    List<Book> booksByGenre(int idGenre);
}
