package com.alternate.sample.repositories;

import com.alternate.sample.entities.UserAccount;

import java.util.Collection;
import java.util.List;

public interface UserRepository {
    UserAccount findById(Long id);
    void save(UserAccount userAccount);
    Collection<UserAccount> findAll();
}
