package com.mlav.springboot.travelagency.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mlav.springboot.travelagency.dto.DiscountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class DiscountModel extends RepresentationModel<DiscountModel> {
    @JsonUnwrapped
    private DiscountDto discountDto;
}
