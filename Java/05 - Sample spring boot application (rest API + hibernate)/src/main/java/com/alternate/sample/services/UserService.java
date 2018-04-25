package com.alternate.sample.services;

import com.alternate.sample.entities.UserAccount;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Iterable<UserAccount> getAllUsers();
    Optional<UserAccount> getUserById(long id);
    void register(UserAccount userAccount);
}
