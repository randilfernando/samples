package com.alternate.sample.services;

import com.alternate.sample.models.User;

public interface UserService {
    boolean login(String username, String password);
    boolean register(User user);
}
