package com.springboot.homework4.model.entity;

import com.springboot.homework4.model.HotelType;
import com.springboot.homework4.model.TourType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
public class Tour extends Entity {
    private String tourName;
    private BigDecimal price;
    private int numberOfTours;
    private int numberOfParticipants;
    private boolean isHot;
    private boolean isDeleted;
    private HotelType hotelType;
    private TourType tourType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tour tour = (Tour) o;
        return numberOfTours == tour.numberOfTours && numberOfParticipants == tour.numberOfParticipants && Objects.equals(tourName, tour.tourName) && Objects.equals(price, tour.price) && hotelType == tour.hotelType && tourType == tour.tourType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tourName, price, numberOfTours, numberOfParticipants, hotelType, tourType);
    }
}
