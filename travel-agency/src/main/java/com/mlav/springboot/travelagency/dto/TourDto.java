package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
import com.mlav.springboot.travelagency.validation.tour.TourPatchUpdate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Builder
public class TourDto {
    private Long id;

    @NotNull(message = "Tour name is a mandatory field")
    private String tourName;

    @NotNull(message = "Price is a mandatory field")
    private BigDecimal price;

    @NotNull(message = "Number of tours is a mandatory field")
    @Min(value = 1, message = "Number of tours can't be less that 1", groups = TourPatchUpdate.class)
    private Integer numberOfTours;

    @NotNull(message = "Number of participants is a mandatory field")
    @Min(value = 1, message = "Number of participants can't be less that 1", groups = TourPatchUpdate.class)
    private Integer numberOfParticipants;

    @NotNull(message = "Hot status is a mandatory field")
    private Boolean isHot;

    @NotNull(message = "Hotel type is a mandatory field")
    private HotelType hotelType;

    @NotNull(message = "Tour type is a mandatory field")
    private TourType tourType;

    @Null(message = "Cannot set this field manually")
    private Boolean isDeleted;
}
