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

    @PutMapping("/user/{id}")
    User updateUserById(@RequestBody User newUser, @PathVariable Long id) {
        /*
            Function to update user based on user ID
         */

        // Return from function while updating user information based on user ID
        return userRepository.findById(id)
                .map(user -> {
                    // Update user details
                    // Get new user details from Path Variable 'newUser'
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());

                    // Write information to database
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUserById(@PathVariable Long id) {
        /*
            Function to delete user based on user ID
         */
        // Check if user ID does not exist in database
        if(!userRepository.existsById(id)) {
            // User ID does not exist
            // Throw a "UserNotFoundException"
            throw new UserNotFoundException(id);
        }
        // Otherwise, user ID exist
        // Delete user ID from database
        userRepository.deleteById(id);
        // Return from function
        return "User with ID: " + id + " has been deleted";
    }
}
