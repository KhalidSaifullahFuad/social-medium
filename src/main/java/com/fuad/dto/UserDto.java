package com.fuad.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @NotBlank(message = "Enter your name")
    private String name;

    @NotBlank(message = "Enter your password")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase, one special character and at least 8 or more characters")
    private String password;

    @NotBlank(message = "Enter your email")
    @Email(message = "Enter valid email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "Enter your location")
    private String location;

}
