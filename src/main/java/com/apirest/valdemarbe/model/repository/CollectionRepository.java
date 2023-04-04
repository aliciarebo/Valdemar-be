package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    @Query("select c from Collection c where c.id = ?1")
    public Collection findOne(String idCollection);
}
