package com.mlav.springboot.travelagency.model.entity;


import com.mlav.springboot.travelagency.model.Role;
import lombok.Data;

@Data
public class Account extends Entity{
    private String login;
    private String password;
    private Role role;
}