package com.springboot.homework4.model;

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

    public String getStar() {
        return this.star;
    }

}
