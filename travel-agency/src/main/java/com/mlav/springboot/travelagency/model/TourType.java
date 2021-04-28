package com.mlav.springboot.travelagency.model;

import com.mlav.springboot.travelagency.model.entity.Tour;

public enum TourType {
    VACATION,
    EXCURSION,
    SHOPPING;

    public String getName() {
        return name().toUpperCase();
    }


    public static TourType getTourType(Tour tour) {
        int tourTypeId = tour.getTourTypeId();
        return TourType.values()[tourTypeId];
    }


}
