package com.apirest.valdemarbe.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.email =?1")
    public User findByEmail(String email);
}
