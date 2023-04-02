package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.Whislist;

public interface WhislistRepository extends JpaRepository<Whislist, Integer> {

    @Query("select w from Whislist w where w.user.email = ?1")
    public Whislist findByUser(String email);

}
