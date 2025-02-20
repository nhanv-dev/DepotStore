package com.spring.server.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private List<String> roles;

    public JwtResponse(String jwt, Long id, String email, String name, String phoneNumber, List<String> roles) {
        this.token = jwt;
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.roles = roles;

    }
}
