package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "collections")
@NamedQuery(name = "Collection.findAll", query = "SELECT w FROM Collection w")
public class Collection implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    @ManyToMany
    @JoinTable(name = "collections_authors", joinColumns = {
            @JoinColumn(name = "id_collection")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_author")
    })

    @JsonIgnore
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "collections_genres", joinColumns = {
            @JoinColumn(name = "id_collection")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_genre")
    })

    @JsonIgnore
    private List<Genre> genres;

    public Collection() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
