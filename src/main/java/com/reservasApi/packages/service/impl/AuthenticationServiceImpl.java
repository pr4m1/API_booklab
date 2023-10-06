package com.reservasApi.packages.service.impl;

import com.reservasApi.packages.controller.models.auth.*;
import com.reservasApi.packages.repository.TokenRepository;
import com.reservasApi.packages.repository.UserRepository;
import com.reservasApi.packages.repository.models.UserE;
import com.reservasApi.packages.security.SecurityConstants;
import com.reservasApi.packages.service.AuthenticationService;
import com.reservasApi.packages.security.service.JwtService;
import com.reservasApi.packages.repository.models.TokenE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        if(repository.existsByUsername(request.getUsername())){
            return ResponseEntity.status(403).body(ErrorAccess.builder()
                    .message("User already exists!")
                    .build());
        }
        if(!checkPassword(request.getPassword())){
            return ResponseEntity.status(403).body(ErrorAccess.builder()
                    .message("Invalid password!\n")
                    .build());
        }
        UserE user = UserE.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(SecurityConstants.USER)
                .build();
        UserE savedUser = repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return ResponseEntity.ok(AccessResponse.builder()
                .access_token(jwtToken)
                .username(user.getUsername())
                .role(user.getAuthority())
                .build());
    }

    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }catch (AuthenticationException a)  {
            return ResponseEntity.status(403).body(ErrorAccess.builder()
                    .message("User or password incorrect!")
                    .build());
        }
        UserE user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return ResponseEntity.ok(AccessResponse.builder()
                .access_token(jwtToken)
                .username(user.getUsername())
                .role(user.getAuthority())
                .build());
    }

    private void saveUserToken(UserE user, String jwtToken) {
        var token = TokenE.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(SecurityConstants.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserE user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(tokenE -> {
            tokenE.setExpired(true);
            tokenE.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    private boolean checkPassword(String password){
        boolean containsLowercase = Pattern.compile("[a-z]").matcher(password).find();
        boolean containsUppercase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean containsDigit = Pattern.compile("\\d").matcher(password).find();
        boolean containsSymbol = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find();
        boolean isLongEnough = password.length() >= 8;
        return containsLowercase && containsUppercase && containsDigit && containsSymbol && isLongEnough;
    }
}
