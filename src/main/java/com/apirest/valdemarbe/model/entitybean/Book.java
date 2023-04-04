package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NamedQuery(name = "Book.findAll", query = "SELECT w FROM Book w")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String isbn;

    private String title;
    private String description;
    private double price;
    private String date;
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "id_collection")
    private Collection collection;

    @ManyToMany
    @JoinTable(name = "books_wishlists", joinColumns = {
            @JoinColumn(name = "id_book")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_wishlist")
    })

    private List<Wishlist> wishlists;

    @ManyToMany
    @JoinTable(name = "books_genres", joinColumns = {
            @JoinColumn(name = "id_book")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_genre")
    })

    private List<Genre> genres;

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

}
