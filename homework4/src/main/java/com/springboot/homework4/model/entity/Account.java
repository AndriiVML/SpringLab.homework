package com.springboot.homework4.model.entity;


import com.springboot.homework4.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
public class Account extends Entity{
    private String login;
    private String password;
    private Role role;
}
