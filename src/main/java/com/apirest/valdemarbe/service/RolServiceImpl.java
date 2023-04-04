package com.apirest.valdemarbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.Rol;
import com.apirest.valdemarbe.model.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepo;

    @Override
    public List<Rol> findAll() {
        return rolRepo.findAll();
    }

}
