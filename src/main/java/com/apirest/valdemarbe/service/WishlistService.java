package com.apirest.valdemarbe.service;

import com.apirest.valdemarbe.model.entitybean.Wishlist;

public interface WishlistService {
    Wishlist findByUser(String email);
}
