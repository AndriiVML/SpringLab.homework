package com.mlav.springboot.travelagency.validation.validator;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.validation.annotations.PasswordsValueMatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValueMatchValidator
        implements ConstraintValidator<PasswordsValueMatchConstraint, Object> {


    public void initialize(PasswordsValueMatchConstraint constraintAnnotation) {
    }

    public boolean isValid(Object obj,
                           ConstraintValidatorContext context) {

        UserDto userDto = (UserDto) obj;

        String fieldValue = userDto.getPassword();

        String fieldMatchValue = userDto.getRepeatPassword();

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
