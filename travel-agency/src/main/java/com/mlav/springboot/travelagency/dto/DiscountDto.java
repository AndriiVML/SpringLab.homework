package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.validation.discount.DiscountCustomAnnotations;
import com.mlav.springboot.travelagency.validation.discount.DiscountDefaultAnnotations;
import com.mlav.springboot.travelagency.validation.discount.annotations.DiscountConstraint;
import lombok.Builder;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@GroupSequence({DiscountDto.class, DiscountDefaultAnnotations.class, DiscountCustomAnnotations.class})
@DiscountConstraint(groups = {DiscountCustomAnnotations.class})
public class DiscountDto {
    private Long id;
    @NotNull(message = "Step is a mandatory field",
            groups = {DiscountDefaultAnnotations.class})
    @Min(value = 0, message = "Step should be more than 0 or equal 0",
            groups = {DiscountDefaultAnnotations.class})
    @Max(value = 100, message = "Step should be less than 100 or equal 100",
            groups = {DiscountDefaultAnnotations.class})
    private Integer step;
    @NotNull(message = "Max is a mandatory field", groups = {DiscountDefaultAnnotations.class})
    @Min(value = 0, message = "Max should be more than 0 or equal 0",
            groups = {DiscountDefaultAnnotations.class})
    @Max(value = 100, message = "Max should be less than 100 or equal 100",
            groups = {DiscountDefaultAnnotations.class})
    private Integer max;
}
