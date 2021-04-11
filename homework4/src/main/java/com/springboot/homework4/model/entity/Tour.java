package com.springboot.homework4.model.entity;

import com.springboot.homework4.model.HotelType;
import com.springboot.homework4.model.TourType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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
}
