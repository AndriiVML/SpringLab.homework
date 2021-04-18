package com.mlav.springboot.travelagency.validation.discount.validator;


import com.mlav.springboot.travelagency.dto.DiscountDto;
import com.mlav.springboot.travelagency.validation.discount.annotations.DiscountConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class DiscountValidator implements
        ConstraintValidator<DiscountConstraint, Object> {


    @Override
    public void initialize(DiscountConstraint emailUnique) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        DiscountDto discountDto = (DiscountDto) obj;
        System.out.println(discountDto);
        return discountDto.getStep() <= discountDto.getMax();
    }

}