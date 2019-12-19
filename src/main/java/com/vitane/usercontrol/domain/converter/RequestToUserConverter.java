package com.vitane.usercontrol.domain.converter;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.domain.UserRequest;
import org.springframework.core.convert.converter.Converter;

public class RequestToUserConverter implements Converter<UserRequest, User> {
    @Override
    public User convert(UserRequest userRequest) {
        return User.builder()
                .login(userRequest.getLogin())
                .email(userRequest.getEmail())
                .build();
    }
}
