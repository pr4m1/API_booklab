package com.reservasApi.packages.controller.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessResponse extends AuthenticationResponse {
    @JsonProperty("access_token")
    private String access_token;

    @JsonProperty("username")
    private String username;

    @JsonProperty("role")
    private String role;
}
