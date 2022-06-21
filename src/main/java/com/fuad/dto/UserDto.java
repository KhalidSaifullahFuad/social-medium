package com.fuad.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase, one special character and at least 8 or more characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "Location is required")
    private String location;

}
