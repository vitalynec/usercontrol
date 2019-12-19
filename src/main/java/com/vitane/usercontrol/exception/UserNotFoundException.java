package com.vitane.usercontrol.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found") //404
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super("User not found:" + message);
    }
}
