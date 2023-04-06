package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "wishlists")
@NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w")
public class Wishlist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToMany
    @JoinTable(name = "books_wishlists", joinColumns = {
            @JoinColumn(name = "id_book")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_wishlist")
    })
    private List<Book> books;

    public Wishlist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
