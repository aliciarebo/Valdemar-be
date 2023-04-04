package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.Book;

public interface BookService {
    List<Book> findAll();

    List<Book> booksByAuthor(int idAuthor);

    List<Book> booksByCollection(String idCollection);

    List<Book> booksOfWishlist(int idWishlist);

    List<Book> findByTitleContaining(String title);

    List<Book> booksByGenre(int idGenre);

    int saveBook(Book book);
}
