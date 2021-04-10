package com.springboot.homework4.model;

public enum TourType {
    VACATION, EXCURSION, SHOPPING;


    public static TourType getTourType(int id) {
        return TourType.values()[id];
    }

    public String getName() {
        return name().toUpperCase();
    }
}
