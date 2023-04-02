package com.apirest.valdemarbe.service;

import com.apirest.valdemarbe.model.entitybean.User;

public interface UserService {
    User findByEmail(String email);

    int saveUser(User user);
}
