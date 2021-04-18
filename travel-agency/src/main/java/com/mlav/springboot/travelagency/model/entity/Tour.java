package com.mlav.springboot.travelagency.model.entity;

import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
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
