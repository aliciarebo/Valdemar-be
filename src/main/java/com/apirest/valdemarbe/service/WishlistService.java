package com.apirest.valdemarbe.service;

import com.apirest.valdemarbe.model.entitybean.Wishlist;

public interface WishlistService {
    Wishlist findOne(int idWishlist);

    Wishlist findByUser(String email);

    int saveWishlist(Wishlist wishlist);
}
