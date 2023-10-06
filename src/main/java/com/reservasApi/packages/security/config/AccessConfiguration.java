package com.reservasApi.packages.security.config;

import com.reservasApi.packages.security.SecurityConstants;
import com.reservasApi.packages.security.component.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class AccessConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/lab/check/**").permitAll()
                .requestMatchers("/lab/all").hasAnyAuthority(SecurityConstants.ADMIN, SecurityConstants.USER)
                .requestMatchers("/lab/manager/**").hasAnyAuthority(SecurityConstants.ADMIN, SecurityConstants.USER)
                .requestMatchers("/lab/**").hasAuthority(SecurityConstants.ADMIN)
                .requestMatchers("/manager/all").hasAnyAuthority(SecurityConstants.ADMIN, SecurityConstants.USER)
                .requestMatchers("/manager/**").hasAuthority(SecurityConstants.ADMIN)
                .requestMatchers("/booking/all").hasAuthority(SecurityConstants.ADMIN)
                .requestMatchers("/booking/**").hasAnyAuthority(SecurityConstants.ADMIN, SecurityConstants.USER)
                .requestMatchers("/user/**").hasAuthority(SecurityConstants.ADMIN)
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
}
