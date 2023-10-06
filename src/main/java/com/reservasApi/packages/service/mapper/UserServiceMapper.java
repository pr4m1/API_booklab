package com.reservasApi.packages.service.mapper;

import com.reservasApi.packages.controller.models.User;
import com.reservasApi.packages.repository.models.UserE;

import java.util.ArrayList;

public interface UserServiceMapper {
    User toController(UserE userE);
    ArrayList<User> toController(ArrayList<UserE> userEs);
}
