package com.apirest.valdemarbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.Collection;
import com.apirest.valdemarbe.model.repository.CollectionRepository;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionRepository collectionRepo;

    @Override
    public List<Collection> findAll() {
        return collectionRepo.findAll();
    }

    @Override
    public Collection findOne(int idCollection) {
        return collectionRepo.findById(idCollection).orElse(null);
    }

}
