package com.springboot.homework4.model;

public enum TourType {
    VACATION,
    EXCURSION,
    SHOPPING;

    public String getName() {
        return name().toUpperCase();
    }

}
