package com.mlav.springboot.travelagency.dto;

import com.springboot.homework4.model.HotelType;
import com.springboot.homework4.model.TourType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class TourDto {
    private long id;
    @NotNull(message = "Tour name is a mandatory field")
    private String tourName;
    @NotNull(message = "Price is a mandatory field")
    private BigDecimal price;
    @Min(value = 1,message = "Number of tours can't be less that 1")
    private int numberOfTours;
    @Min(value = 1, message = "Number of participants can't be less that 1")
    private int numberOfParticipants;
    private boolean isHot;
    @NotNull(message = "Hotel type is a mandatory field")
    private HotelType hotelType;
    @NotNull(message = "Tour type is a mandatory field")
    private TourType tourType;
}
