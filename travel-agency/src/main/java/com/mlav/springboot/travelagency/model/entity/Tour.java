package com.mlav.springboot.travelagency.model.entity;

import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Tour{
    private Long id;
    private String tourName;
    private BigDecimal price;
    private Integer numberOfTours;
    private Integer numberOfParticipants;
    private Boolean isHot;
    private Boolean isDeleted;
    private HotelType hotelType;
    private TourType tourType;
}
