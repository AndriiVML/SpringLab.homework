package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
import com.mlav.springboot.travelagency.validation.tour.TourDefaultAnnotations;
import com.mlav.springboot.travelagency.validation.tour.TourPatchUpdate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
public class TourDto {
    private Long id;

    @NotNull(message = "Tour name is a mandatory field", groups = TourDefaultAnnotations.class)
    @Pattern(regexp = "[A-Za-z ]+", message = "Tour name should include only latin letters and whitespace"
            , groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    @Size(min = 3, message = "Too short name of tour. Tour name should have at least 3 symbols"
            , groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    @Size(max = 30, message = "Too long name of tour. Tour name cannot exceed 30 symbols"
            , groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    private String tourName;

    @NotNull(message = "Price is a mandatory field", groups = TourDefaultAnnotations.class)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price can't be zero or less than zero"
            , groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    @DecimalMax(value = "99999.99", message = "Price can't be more than 100_000.00 or equal"
            , groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    private BigDecimal price;

    @NotNull(message = "Number of tours is a mandatory field",
            groups = {TourDefaultAnnotations.class, TourDefaultAnnotations.class})
    @Min(value = 1, message = "Number of tours can't be less that 1",
            groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    private Integer numberOfTours;

    @NotNull(message = "Number of participants is a mandatory field", groups = TourDefaultAnnotations.class)
    @Min(value = 1, message = "Number of participants can't be less that 1",
            groups = {TourPatchUpdate.class, TourDefaultAnnotations.class})
    private Integer numberOfParticipants;

    @NotNull(message = "Hot status is a mandatory field", groups = TourDefaultAnnotations.class)
    private Boolean isHot;

    @NotNull(message = "Hotel type is a mandatory field", groups = TourDefaultAnnotations.class)
    private HotelType hotelType;

    @NotNull(message = "Tour type is a mandatory field", groups = TourDefaultAnnotations.class)
    private TourType tourType;

    @Null(message = "Cannot set this field manually", groups = TourDefaultAnnotations.class)
    private Boolean isDeleted;
}
