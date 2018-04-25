package com.alternate.sample.services;

import com.alternate.sample.entities.UserAccount;
import com.alternate.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserAccount getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void register(UserAccount userAccount) {
        userRepository.save(userAccount);
    }

}
