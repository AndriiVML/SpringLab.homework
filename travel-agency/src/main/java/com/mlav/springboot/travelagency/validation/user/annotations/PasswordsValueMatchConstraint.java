package com.mlav.springboot.travelagency.validation.user.annotations;

import com.mlav.springboot.travelagency.validation.user.validator.PasswordValueMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsValueMatchConstraint {
    String message() default "Password do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

