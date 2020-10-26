package com.lab2.auth.service;

import com.lab2.auth.dao.UserRepository;
import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    public UserRepository userRepo;

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User validateUser(Login login) {
        return userRepo.findById(login.getUsername()).get();
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
