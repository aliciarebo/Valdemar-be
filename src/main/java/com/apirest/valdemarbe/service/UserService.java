package com.apirest.valdemarbe.service;

import java.util.List;

import com.apirest.valdemarbe.model.entitybean.User;

public interface UserService {
    User findByEmail(String email);

    int saveUser(User user);

    List<User> findByRol(int idRol);
}
