package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
@NamedQuery(name = "Author.findAll", query = "SELECT w FROM Author w")
public class Author implements Serializable {
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
    private List<Collection> collections;

    public Author() {
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

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return "Author [name=" + name + "]";
    }

}
