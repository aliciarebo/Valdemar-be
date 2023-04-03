package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.valdemarbe.model.entitybean.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
