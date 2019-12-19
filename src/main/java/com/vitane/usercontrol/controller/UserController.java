package com.vitane.usercontrol.controller;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.domain.UserResponse;
import com.vitane.usercontrol.exception.UserNotFoundException;
import com.vitane.usercontrol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping({"", "/"})
    public ResponseEntity<Page<UserResponse>> getNonDeletedUsers(@RequestParam(value = "login", required = false) String login,
                                                                 @RequestParam(value = "email", required = false) String email,
                                                                 @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.read(login, email, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.read(id));
    }

    @PostMapping(value = "/{id}/pwd", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setUserPassword(@PathVariable UUID id,
                                          @RequestBody String lastPassword,
                                          @RequestBody String newPassword) throws Exception {
        userService.setPassword(id, lastPassword, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.created(URI.create("/" + user.getId())).build();
    }

    @PostMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUser(@RequestBody User user) throws UserNotFoundException {
        return ResponseEntity.ok(userService.update(user));
    }

    @GetMapping(value = "/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
