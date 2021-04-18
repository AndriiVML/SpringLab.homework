package com.mlav.springboot.travelagency.validation.user.annotations;



import com.mlav.springboot.travelagency.validation.user.validator.LoginUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUniqueConstraint {
    String message() default "Login must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}