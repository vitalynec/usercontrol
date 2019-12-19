package com.vitane.usercontrol.domain.converter;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.domain.UserResponse;
import org.springframework.core.convert.converter.Converter;

public class UserToResponseConverter implements Converter<User, UserResponse> {
    @Override
    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
