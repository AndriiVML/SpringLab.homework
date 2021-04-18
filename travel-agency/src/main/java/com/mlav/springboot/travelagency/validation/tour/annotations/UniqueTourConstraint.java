package com.mlav.springboot.travelagency.validation.tour.annotations;

import com.mlav.springboot.travelagency.validation.tour.validator.UniqueTourValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueTourValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTourConstraint {
    String message() default "There is already the same tour with the same fields";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

