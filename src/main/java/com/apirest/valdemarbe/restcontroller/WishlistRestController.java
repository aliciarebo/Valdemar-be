package com.apirest.valdemarbe.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Wishlist;
import com.apirest.valdemarbe.service.WishlistService;

@RestController
@RequestMapping("/rest/whislist")
public class WishlistRestController {

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/{email}")
    ResponseEntity<?> findWhislist(@PathVariable("email") String email) {
        return new ResponseEntity<Wishlist>(wishlistService.findByUser(email), HttpStatus.OK);
    }
}
