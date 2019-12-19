package com.vitane.usercontrol.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Password not matches") //401
public class PasswordNotMatchesException extends Exception {
    public PasswordNotMatchesException(String message) {
        super(message);
    }
}
