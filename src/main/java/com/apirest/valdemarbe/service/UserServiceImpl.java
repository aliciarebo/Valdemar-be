package com.apirest.valdemarbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.valdemarbe.model.entitybean.User;
import com.apirest.valdemarbe.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public int saveUser(User user) {
        try {
            userRepo.save(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<User> findByRol(int idRol) {
        return userRepo.findByRol(idRol);
    }

}
