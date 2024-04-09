package org.example.thelooker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String address;
    @Email
    @NotBlank(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;
    private String role;
    @NotEmpty(message = "Username is required")
    private String username;
}
