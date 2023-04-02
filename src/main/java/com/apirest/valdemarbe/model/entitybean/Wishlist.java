package com.apirest.valdemarbe.model.entitybean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "wishlists")
@NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w")
public class Wishlist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "email_user")
    private User user;

    public Wishlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
