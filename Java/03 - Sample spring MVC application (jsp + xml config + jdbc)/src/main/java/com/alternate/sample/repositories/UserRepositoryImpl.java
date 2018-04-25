package com.alternate.sample.repositories;

import com.alternate.sample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        //language=SQL
        String query = "SELECT * FROM user_account WHERE username=? AND password=?";
        return jdbcTemplate.queryForObject(query, new Object[]{username, password}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println();
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        });
    }

    @Override
    public boolean addUser(User user) {
        //language=SQL
        String query = "INSERT INTO user_account (username, password) VALUES (?, ?)";
        jdbcTemplate.update(query, user.getUsername(), user.getPassword());
        return true;
    }
}
