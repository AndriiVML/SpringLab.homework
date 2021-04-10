package com.springboot.homework4.model;

public enum Role {
    ADMIN, USER, MODERATOR;


    public static Role getRole(int id) {
        return Role.values()[id];
    }
    public String getName() {
        return name().toLowerCase();
    }
}
