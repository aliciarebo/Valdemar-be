package com.apirest.valdemarbe.service;

import com.apirest.valdemarbe.model.entitybean.Wishlist;

public interface WishlistService {
    Wishlist findOne(String id);

    int saveWishlist(Wishlist wishlist);
}
