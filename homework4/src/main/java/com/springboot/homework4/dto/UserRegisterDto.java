package com.springboot.homework4.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterDto {
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
}
