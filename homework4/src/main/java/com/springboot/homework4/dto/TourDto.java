package com.springboot.homework4.dto;

import com.springboot.homework4.model.HotelType;
import com.springboot.homework4.model.TourType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TourDto {
    private long id;
    private String tourName;
    private BigDecimal price;
    private int numberOfTours;
    private int numberOfParticipants;
    private boolean isHot;
    private HotelType hotelType;
    private TourType tourType;
}
