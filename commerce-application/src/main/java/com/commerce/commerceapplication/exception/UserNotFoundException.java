package com.commerce.commerceapplication.exception;

public class UserNotFoundException extends RuntimeException {
    /*
        Class for handling exception when user is not found
    */
    public UserNotFoundException(Long id) {
        /*
            Constructor for the class
         */
        // Call the 'RuntimeException' class constructor
        super("Could not find the user with id: " + id);
    }
}
