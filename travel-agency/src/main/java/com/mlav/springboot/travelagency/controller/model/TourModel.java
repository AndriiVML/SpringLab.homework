package com.mlav.springboot.travelagency.controller.model;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mlav.springboot.travelagency.dto.TourDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TourModel extends RepresentationModel<TourModel> {
    @JsonUnwrapped
    private TourDto tourDto;
}
