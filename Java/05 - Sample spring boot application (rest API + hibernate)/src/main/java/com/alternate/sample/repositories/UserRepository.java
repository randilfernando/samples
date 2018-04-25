package com.alternate.sample.repositories;

import com.alternate.sample.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by randil on 8/27/17.
 */
public interface UserRepository extends CrudRepository<UserAccount, Long> {
}
