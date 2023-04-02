package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.valdemarbe.model.entitybean.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

}
