package com.commerce.commerceapplication.controller;

import com.commerce.commerceapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.commerce.commerceapplication.model.User;

import java.util.List;

@RestController
public class UserController {

    // Inject UserRepository to UserController class
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        /*
        Function to POST data to the database
         */

        // Return from function saving data to database
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        /*
        Function to GET all users
         */

        // Return list of all users
        return userRepository.findAll();
    }
}
