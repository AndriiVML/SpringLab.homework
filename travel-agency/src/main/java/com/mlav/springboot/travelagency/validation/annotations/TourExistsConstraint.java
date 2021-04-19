package com.mlav.springboot.travelagency.validation.annotations;

import com.mlav.springboot.travelagency.validation.validator.TourExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TourExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TourExistsConstraint {
    String message() default "You cannot make a purchase. Tour does not exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

