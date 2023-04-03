package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "collections")
@NamedQuery(name = "Collection.findAll", query = "SELECT w FROM Collection w")
public class Collection implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // uni-directional many-to-many association to Movie
    // @ManyToMany
    // @JoinTable(name = "collections_authors", joinColumns = {
    // @JoinColumn(name = "id_collection")
    // }, inverseJoinColumns = {
    // @JoinColumn(name = "id_author")
    // })
    // private List<Author> authors;

    public Collection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public List<Author> getAuthors() {
    // return authors;
    // }

    // public void setAuthors(List<Author> authors) {
    // this.authors = authors;
    // }

}
