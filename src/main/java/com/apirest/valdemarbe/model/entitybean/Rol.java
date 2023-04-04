package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "rols")
@NamedQuery(name = "Rol.findAll", query = "SELECT u FROM Rol u")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;

    public Rol() {
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
