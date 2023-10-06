package com.reservasApi.packages.service.impl;

import com.reservasApi.packages.controller.models.User;
import com.reservasApi.packages.exception.ApiException;
import com.reservasApi.packages.repository.UserRepository;
import com.reservasApi.packages.repository.models.UserE;
import com.reservasApi.packages.security.service.JwtService;
import com.reservasApi.packages.service.UserService;
import com.reservasApi.packages.service.mapper.UserServiceMapper;
import com.reservasApi.packages.service.mapper.impl.UserServiceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    private UserServiceMapper userServiceMapper;

    public UserServiceImpl(){
        userServiceMapper = new UserServiceMapperImpl();
    }

    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails)authentication.getPrincipal()).getUsername();
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<UserE> userEs = (ArrayList<UserE>) userRepository.findAll();
        return  userServiceMapper.toController(userEs);
    }
    @Override
    public void deleteUser(long id) {
        User user = getUserById(id);
        userRepository.deleteById(user.getId());
    }
    @Override
    public String getAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserE user = userRepository.findByUsername(((UserDetails)authentication.getPrincipal()).getUsername()).orElseThrow(()->new ApiException("This user doesn't exist!"));
        return user.getAuthority();
    }
    private User getUserById(long id) {
        UserE userE = userRepository.findById(id).orElseThrow(()->new ApiException("Not user with id '" + id + "' exists."));
        return userServiceMapper.toController(userE);
    }
}
