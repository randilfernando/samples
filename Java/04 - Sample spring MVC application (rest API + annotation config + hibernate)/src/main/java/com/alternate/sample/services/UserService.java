package com.alternate.sample.services;

import com.alternate.sample.entities.UserAccount;

import java.util.Collection;

public interface UserService {
    Collection<UserAccount> getAllUsers();
    UserAccount getUserById(long id);
    void register(UserAccount userAccount);
}
