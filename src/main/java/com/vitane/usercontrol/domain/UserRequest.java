package com.vitane.usercontrol.domain;

import lombok.Data;

@Data
public class UserRequest {
    private String login;
    private String email;
    private String name;
}
