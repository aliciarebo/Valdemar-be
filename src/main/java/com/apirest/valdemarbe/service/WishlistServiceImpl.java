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
    public Wishlist findOne(String id) {
        return wishlistRepo.findOne(id);
    }

    @Override
    public int saveWishlist(Wishlist wishlist) {
        try {
            wishlistRepo.save(wishlist);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
