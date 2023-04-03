package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "genres")
@NamedQuery(name = "Genre.findAll", query = "SELECT w FROM Genre w")
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Genre() {
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

}
