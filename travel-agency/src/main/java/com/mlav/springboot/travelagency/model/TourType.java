package com.mlav.springboot.travelagency.model;

public enum TourType {
    VACATION,
    EXCURSION,
    SHOPPING;

    public String getName() {
        return name().toUpperCase();
    }

}
