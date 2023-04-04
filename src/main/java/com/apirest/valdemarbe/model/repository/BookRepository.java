package com.apirest.valdemarbe.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.valdemarbe.model.entitybean.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.author.id = ?1")
    public List<Book> booksByAuthor(int idAuthor);

    @Query("select b from Book b join b.wishlists w where w.id=?1")
    public List<Book> booksOfWishlist(int idWishlist);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    public List<Book> findByTitleContaining(@Param("title") String title);

    @Query("select b from Book b join b.genres g where g.id = ?1")
    public List<Book> booksByGenre(int idGenre);

    @Query("select b from Book b where b.collection.id = ?1")
    public List<Book> booksByCollection(String idCollection);

    @Query("select b from Book b where b.isbn = ?1")
    public Book findOne(String isbn);
}
