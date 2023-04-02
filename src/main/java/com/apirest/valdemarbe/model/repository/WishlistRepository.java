package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    @Query("select w from Wishlist w where w.user.email = ?1")
    public Wishlist findByUser(String email);

}
