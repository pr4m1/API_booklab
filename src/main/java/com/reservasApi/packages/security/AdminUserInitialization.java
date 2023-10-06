package com.reservasApi.packages.security;

import com.reservasApi.packages.repository.UserRepository;
import com.reservasApi.packages.repository.models.UserE;
import com.reservasApi.packages.security.SecurityConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class AdminUserInitialization {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initializeData() {
        if (!userRepository.existsByUsername("admin")) {
            UserE adminUser = new UserE();
            adminUser.setUsername("admin");
            adminUser.setPassword(new BCryptPasswordEncoder().encode("Admin1234"));
            adminUser.setAuthority(SecurityConstants.ADMIN);

            userRepository.save(adminUser);
        }
    }
}
