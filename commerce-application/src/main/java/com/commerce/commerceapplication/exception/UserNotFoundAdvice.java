package com.commerce.commerceapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundAdvice {
    /*
        Class to handle exception logging to console
     */
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(UserNotFoundException exception) {
        /*
            Function to handle cases when Controller class encounters "UserNotFoundException"
            - This means that the user ID was not found.
            - The API will respond back with a JSON response.
            - Key for the JSON will be "errorMessage".
            - Value will be the value of "exception.getMessage()".
            - The value for "exception.getMessage()" is being pulled from "UserNotFoundException" constructor.
         */

        // Create a new error HashMap and push error message to the HashMap
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());

        // Return error HashMap
        return errorMap;
    }
}
