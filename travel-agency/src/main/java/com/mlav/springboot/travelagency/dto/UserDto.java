package com.mlav.springboot.travelagency.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {
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
    @Null(message = "You don't have permission to change discount")
    private Integer discount;
    private boolean isBlocked;

}
