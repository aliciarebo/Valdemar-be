package com.apirest.valdemarbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.Wishlist;
import com.apirest.valdemarbe.model.repository.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepo;

    @Override
    public Wishlist findByUser(String email) {
        return wishlistRepo.findByUser(email);
    }

}
