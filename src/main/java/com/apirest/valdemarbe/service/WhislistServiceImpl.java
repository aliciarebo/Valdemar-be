package com.apirest.valdemarbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.Whislist;
import com.apirest.valdemarbe.model.repository.WhislistRepository;

@Service
public class WhislistServiceImpl implements WhislistService {

    @Autowired
    WhislistRepository whislistRepo;

    @Override
    public Whislist findByUser(String email) {
        return whislistRepo.findByUser(email);
    }

}
