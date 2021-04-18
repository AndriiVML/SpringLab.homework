package com.mlav.springboot.travelagency.validation.discount.annotations;



import com.mlav.springboot.travelagency.validation.discount.validator.DiscountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DiscountValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DiscountConstraint {
    String message() default "Max cannot be less that step";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

