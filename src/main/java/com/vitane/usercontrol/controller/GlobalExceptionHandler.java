package com.vitane.usercontrol.controller;

import com.vitane.usercontrol.exception.ExceptionInfo;
import com.vitane.usercontrol.exception.PasswordNotMatchesException;
import com.vitane.usercontrol.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ExceptionInfo handlerUserNotFoundException(HttpServletRequest request, Exception ex) {
        ExceptionInfo response = new ExceptionInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        return response;
    }

    @ExceptionHandler(PasswordNotMatchesException.class)
    public void handlerPasswordNotMatchesException() {
    }

    @ExceptionHandler
    @ResponseBody
    public ExceptionInfo handlerException(HttpServletRequest request, Exception ex) {
        ExceptionInfo response = new ExceptionInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        return response;
    }
}
