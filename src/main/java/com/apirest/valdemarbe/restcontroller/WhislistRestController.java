package com.apirest.valdemarbe.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Whislist;
import com.apirest.valdemarbe.service.WhislistService;

@RestController
@RequestMapping("/rest/whislist")
public class WhislistRestController {

    @Autowired
    WhislistService whislistService;

    @GetMapping("/{email}")
    ResponseEntity<?> findWhislist(@PathVariable("email") String email) {
        return new ResponseEntity<Whislist>(whislistService.findByUser(email), HttpStatus.OK);
    }
}
