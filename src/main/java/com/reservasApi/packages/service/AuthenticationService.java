package com.reservasApi.packages.service;


import com.reservasApi.packages.controller.models.auth.AuthenticationRequest;
import com.reservasApi.packages.controller.models.auth.AuthenticationResponse;
import com.reservasApi.packages.controller.models.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);
    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);

}
