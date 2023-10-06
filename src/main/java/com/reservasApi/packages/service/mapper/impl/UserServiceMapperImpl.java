package com.reservasApi.packages.service.mapper.impl;

import com.reservasApi.packages.controller.models.User;
import com.reservasApi.packages.repository.models.UserE;
import com.reservasApi.packages.service.mapper.UserServiceMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserServiceMapperImpl implements UserServiceMapper {

    public UserServiceMapperImpl() {
    }

    @Override
    public User toController(UserE userE) {
        User user = new User();
        user.setId(userE.getId());
        user.setUsername(userE.getUsername());
        return user;
    }

    @Override
    public ArrayList<User> toController(ArrayList<UserE> userEs) {
        return (ArrayList<User>)userEs.stream().map(this::toController).collect(Collectors.toList());
    }
}
