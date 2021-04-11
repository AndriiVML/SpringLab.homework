package com.springboot.homework4.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int discount;
    private boolean isBlocked;

}
