package com.mlav.springboot.travelagency.validation.order.validator;

import com.mlav.springboot.travelagency.dto.TourDto;
import com.mlav.springboot.travelagency.exception.TourNotFoundException;
import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.service.TourService;
import com.mlav.springboot.travelagency.validation.order.annotations.TourExistsConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class TourExistsValidator
        implements ConstraintValidator<TourExistsConstraint, Long> {

    private final TourService tourService;


    public void initialize(TourExistsConstraint constraintAnnotation) {
    }

    public boolean isValid(Long id,
                           ConstraintValidatorContext context) {

        boolean exists = true;
        try {
            tourService.getTour(id);
        } catch (TourNotFoundException ex) {
            exists = false;
        }
        return exists;
    }
}
