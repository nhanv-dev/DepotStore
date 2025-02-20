package com.spring.server.payload.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @Email
    @Size(max = 256)
    private String email;
    @NotBlank
    @Size(max = 256)
    private String name;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;

    private Set<String> role;

}
