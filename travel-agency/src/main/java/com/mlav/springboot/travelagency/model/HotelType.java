package com.mlav.springboot.travelagency.model;

import com.mlav.springboot.travelagency.model.entity.Tour;

public enum HotelType {
    ONE_STAR("*"),
    TWO_STAR("**"),
    THREE_STAR("***"),
    FOUR_STAR(("****")),
    FIVE_STAR("*****");

    private String star;

    HotelType(String star) {
        this.star = star;
    }

    public static HotelType getHotelType(Tour tour){
        int hotelTypeId = tour.getHotelTypeId();
        return HotelType.values()[hotelTypeId];
    }

    public String getStar() {
        return this.star;
    }

}
