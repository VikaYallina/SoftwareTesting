package com.lab2.auth.service;

import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(User user);

    User validateUser(Login login);

    User findByUsername(String username);
}
