package com.apirest.valdemarbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.Book;
import com.apirest.valdemarbe.model.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepo;

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> booksByCollection(String idCollection) {
        return bookRepo.booksByCollection(idCollection);
    }

    @Override
    public List<Book> findByTitleContaining(String title) {
        return bookRepo.findByTitleContaining(title);
    }

    @Override
    public int saveBook(Book book) {
        try {
            bookRepo.save(book);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Book findOne(String isbn) {
        return bookRepo.findOne(isbn);
    }

    @Override
    public List<Book> filteredBooks(String idCollection, String[] genres, String[] authors) {
        return bookRepo.filteredBooks(idCollection, genres, authors);
    }

    @Override
    public int deleteBook(Book book) {
        try {
            bookRepo.delete(book);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

}
