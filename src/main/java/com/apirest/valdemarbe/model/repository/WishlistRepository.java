package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, String> {

    @Query("select w from Wishlist w where w.id = ?1")
    public Wishlist findOne(String id);

}
