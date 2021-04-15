package com.mlav.springboot.travelagency.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRegisterDto {
    private long id;
    @NotNull(message = "Login is a mandatory field")
    private String login;
    @NotNull(message = "First name is a mandatory field")
    private String firstName;
    @NotNull(message = "Last name is a mandatory field")
    private String lastName;
    @Email
    @NotNull(message = "Email is a mandatory field")
    private String email;
    @NotNull(message = "Password is a mandatory field")
    private String password;
    @NotNull(message = "Repeat password is a mandatory field")
    private String repeatPassword;
}
