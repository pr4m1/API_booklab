package com.reservasApi.packages.controller;

import com.reservasApi.packages.controller.models.User;
import com.reservasApi.packages.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ArrayList<User> getAllUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") long id){
        userService.deleteUser(id);
    }
}
