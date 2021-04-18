package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
import com.mlav.springboot.travelagency.validation.tour.TourCustomAnnotations;
import com.mlav.springboot.travelagency.validation.tour.TourDefaultAnnotations;
import com.mlav.springboot.travelagency.validation.tour.TourPatchUpdate;
import com.mlav.springboot.travelagency.validation.tour.TourPutUpdate;
import com.mlav.springboot.travelagency.validation.tour.annotations.UniqueTourConstraint;
import com.mlav.springboot.travelagency.validation.user.UserPatchUpdate;
import com.mlav.springboot.travelagency.validation.user.UserPutUpdate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
@GroupSequence({TourDto.class, TourDefaultAnnotations.class,TourCustomAnnotations.class})
@UniqueTourConstraint(groups = {TourCustomAnnotations.class,TourPutUpdate.class,TourPatchUpdate.class})
public class TourDto {
    @EqualsAndHashCode.Exclude
    private Long id;

    @EqualsAndHashCode.Include
    @NotNull(message = "Tour name is a mandatory field",groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    private String tourName;

    @EqualsAndHashCode.Include
    @NotNull(message = "Price is a mandatory field",groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    private BigDecimal price;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "Number of tours is a mandatory field",
            groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    @Min(value = 1,message = "Number of tours can't be less that 1"
            ,groups = {TourDefaultAnnotations.class,TourPatchUpdate.class, TourPutUpdate.class})
    private Integer numberOfTours;

    @EqualsAndHashCode.Include
    @NotNull(message = "Number of participants is a mandatory field",
            groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    @Min(value = 1, message = "Number of participants can't be less that 1"
            ,groups = {TourDefaultAnnotations.class,TourPatchUpdate.class, TourPutUpdate.class})
    private Integer numberOfParticipants;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "Hot status is a mandatory field",groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    private Boolean isHot;

    @EqualsAndHashCode.Include
    @NotNull(message = "Hotel type is a mandatory field",groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    private HotelType hotelType;

    @EqualsAndHashCode.Include
    @NotNull(message = "Tour type is a mandatory field",groups = {TourPutUpdate.class,TourDefaultAnnotations.class})
    private TourType tourType;
}
