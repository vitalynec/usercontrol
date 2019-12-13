package com.vitane.usercontrol.controller;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.service.UserService;
import com.vitane.usercontrol.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final int PAGE_SIZE = 10;
    Specification<User> nonDeletedSpecification = UserSpecification.findNonDeleted();

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getUnDeletedUsers(@RequestParam Map<String, String> params) {
        int page = Integer.parseInt(params.getOrDefault("page", "0"));
        List<User> userList = userService.read(nonDeletedSpecification, PageRequest.of(page, PAGE_SIZE));
        return ResponseEntity.ok(userList);
    }

}
