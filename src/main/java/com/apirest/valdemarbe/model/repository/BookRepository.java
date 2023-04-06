package com.apirest.valdemarbe.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.valdemarbe.model.entitybean.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    public List<Book> findByTitleContaining(@Param("title") String title);

    @Query("select b from Book b where b.collection.id = ?1")
    public List<Book> booksByCollection(String idCollection);

    @Query("select b from Book b where b.isbn = ?1")
    public Book findOne(String isbn);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE b.collection.id = ?1 AND (g.name IN (?2) OR b.author.name IN (?3))")
    public List<Book> filteredBooks(String idCollection, String[] genres, String[] authors);

}
