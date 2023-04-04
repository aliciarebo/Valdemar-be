package com.apirest.valdemarbe.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.valdemarbe.model.entitybean.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.email =?1")
    public User findByEmail(String email);

    @Query("select u from User u where u.rol.id=?1")
    public List<User> findByRol(int idRol);
}
