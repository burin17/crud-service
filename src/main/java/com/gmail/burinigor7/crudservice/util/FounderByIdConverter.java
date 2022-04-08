package com.gmail.burinigor7.crudservice.util;

import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FounderByIdConverter implements Converter<Long, User> {
    private final UserService userService;

    @Autowired
    public FounderByIdConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(Long userId) {
        return userService.user(userId);
    }
}
