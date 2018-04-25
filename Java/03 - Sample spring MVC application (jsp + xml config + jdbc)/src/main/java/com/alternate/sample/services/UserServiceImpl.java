package com.alternate.sample.services;

import com.alternate.sample.models.User;
import com.alternate.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        return user != null;
    }

    @Override
    public boolean register(User user) {
        return userRepository.addUser(user);
    }
}
