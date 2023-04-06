package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.Book;

public interface BookService {
    List<Book> findAll();

    List<Book> booksByCollection(String idCollection);

    List<Book> findByTitleContaining(String title);

    int saveBook(Book book);

    Book findOne(String isbn);

    List<Book> filteredBooks(String idCollection, String[] genres, String[] authors);

    int deleteBook(Book book);
}
