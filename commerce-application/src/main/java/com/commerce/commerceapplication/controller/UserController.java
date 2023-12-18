package com.commerce.commerceapplication.controller;

import com.commerce.commerceapplication.dto.UserResponse;
import com.commerce.commerceapplication.repository.UserRepository;
import com.commerce.commerceapplication.model.User;
import com.commerce.commerceapplication.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {

    // Define variable to store user repository object
    private final UserRepository userRepository;

    // Define constructor for user repository injection
    @Autowired
    public UserController(UserRepository userRepository) {
        // Inject UserRepository to UserController class
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) {
        /*
        Function to POST data to the database
         */
        // Return from function saving data to database
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    UserResponse getAllUsers(Pageable pageable) {
        /*
        Function to GET all users
        - Pageable functionality included to get subset of data
        - Example URL to access pageable data -> "http://localhost:8080/api/v1/users?page=0&size=2"
        - Provide parameters to API URL "page" and "size"
        - Pageable response also has metadata for page
         */
        // Declare a Map to store "user" information
        UserResponse response = new UserResponse();

        // Retrieve a page of users
        Page<User> userPage = userRepository.findAll(pageable);

        // Populate HashMap with required user details
        response.setUsers(userPage.getContent());
        response.setPageNo(userPage.getNumber());
        response.setPageSize(userPage.getSize());
        response.setTotalElements(userPage.getTotalElements());
        response.setTotalPages(userPage.getTotalPages());
        response.setLast(userPage.isLast());

        // Return list of all users
        return response;
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    User getUserById(@PathVariable Long id) {
        /*
            Function to get user details based on user ID
         */
        // Return user with passed 'id'
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
