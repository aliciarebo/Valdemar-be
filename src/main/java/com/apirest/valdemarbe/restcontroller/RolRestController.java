package com.apirest.valdemarbe.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.valdemarbe.model.entitybean.Rol;
import com.apirest.valdemarbe.service.RolService;

@RestController
@RequestMapping("/rest/rol")
public class RolRestController {

    @Autowired
    RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() {
        return new ResponseEntity<List<Rol>>(rolService.findAll(), HttpStatus.OK);
    }

}
