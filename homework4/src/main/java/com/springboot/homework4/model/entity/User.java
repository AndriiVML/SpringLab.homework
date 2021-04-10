package com.springboot.homework4.model.entity;

import lombok.Data;

@Data
public class User extends Account{
    private String firstName;
    private String lastName;
    private boolean isBlocked;
    private String email;
    private int discount;
}
