package com.alternate.sample.controllers;

import com.alternate.sample.entities.UserAccount;
import com.alternate.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by randil on 8/27/17.
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * ResponseEntity wrapper class is used when sending responses
     * easy to send different type of responses (eg: 200, 404)
     */

    @GetMapping
    public ResponseEntity<Iterable<UserAccount>> getAllUsers(){
        Iterable<UserAccount> userAccounts = userService.getAllUsers();
        return ResponseEntity.ok(userAccounts);
    }

    /*
     * when defining return type wildcard character is used "?"
     * because when there is 404 error we cant send UserAccount type
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        Optional<UserAccount> result = this.userService.getUserById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build(); //build and send 404 response without response body
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @NotNull UserAccount userAccount) {
        userService.register(userAccount);
        return ResponseEntity.ok().build(); //build and send response without response body
    }
}
