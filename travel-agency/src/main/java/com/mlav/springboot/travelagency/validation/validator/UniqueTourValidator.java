package com.mlav.springboot.travelagency.validation.validator;

import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.service.TourService;
import com.mlav.springboot.travelagency.validation.annotations.UniqueTourConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueTourValidator
        implements ConstraintValidator<UniqueTourConstraint, Object> {

    private final TourService tourService;


    public void initialize(UniqueTourConstraint constraintAnnotation) {
    }

    public boolean isValid(Object obj,
                           ConstraintValidatorContext context) {


        TourDto tourDto = (TourDto) obj;
        boolean notUnique = tourService.getAllTours().stream()
                .anyMatch(t -> t.equals(tourDto));

        return !notUnique;
    }
}
