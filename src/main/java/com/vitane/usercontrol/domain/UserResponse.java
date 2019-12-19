package com.vitane.usercontrol.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String login;
    private String email;
    private String name;
}
