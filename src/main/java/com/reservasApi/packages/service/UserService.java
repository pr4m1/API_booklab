package com.reservasApi.packages.service;

import com.reservasApi.packages.controller.models.User;

import java.util.ArrayList;

public interface UserService {
    String getUsername();
    ArrayList<User> getUsers();
    void deleteUser(long id);
    String getAuthority();
}
