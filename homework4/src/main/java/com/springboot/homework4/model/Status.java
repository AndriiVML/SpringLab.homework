package com.springboot.homework4.model;

public enum Status {
    REGISTERED, PAID, CANCELLED;

    public static Status getStatus(int id) {
        return Status.values()[id];
    }

    public String getName() {
        return name().toUpperCase();
    }
}
