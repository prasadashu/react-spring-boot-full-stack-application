package com.commerce.commerceapplication.controller;

import com.commerce.commerceapplication.repository.UserRepository;
import com.commerce.commerceapplication.model.User;
import com.commerce.commerceapplication.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        // Return user with passed 'id'
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
