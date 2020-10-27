package com.lab2.auth.service;

import com.lab2.auth.dao.UserRepository;
import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    public UserRepository userRepo;

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User validateUser(Login login) {
        Optional<User> user = userRepo.findById(login.getUsername());
        if (user.isPresent() && login.getPassword().equals(user.get().getPassword()))
            return user.get();
        else return null;

    }

    public User findByUsername(String name){
        Optional<User> user = userRepo.findById(name);
        return user.orElse(null);
    }
}
