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
    public Collection findOne(String idCollection) {
        return collectionRepo.findOne(idCollection);
    }

    @Override
    public int saveCollection(Collection collection) {
        try {
            collectionRepo.save(collection);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

}
