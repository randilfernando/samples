package com.alternate.sample.repositories;

import com.alternate.sample.models.User;

import java.util.Map;

public interface UserRepository {
    User getUserByUsernameAndPassword(String username, String password);
    boolean addUser(User user);
}
