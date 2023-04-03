package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.Collection;

public interface CollectionService {
    List<Collection> findAll();

    Collection findOne(int idCollection);
}
