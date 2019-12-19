package com.vitane.usercontrol.service;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.domain.UserResponse;
import com.vitane.usercontrol.exception.PasswordNotMatchesException;
import com.vitane.usercontrol.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    void create(User user);
    UserResponse read(UUID id) throws UserNotFoundException;
    UserResponse update(User user) throws UserNotFoundException;
    void delete(UUID id) throws UserNotFoundException;
    void setPassword(UUID id, String lastPassword, String newPassword)
            throws UserNotFoundException, PasswordNotMatchesException;
    Page<UserResponse> read(String login, String email, Pageable pageable);
}
