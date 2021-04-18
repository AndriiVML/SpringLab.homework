package com.mlav.springboot.travelagency.validation.user.validator;

import com.mlav.springboot.travelagency.dto.UserRegisterDto;
import com.mlav.springboot.travelagency.validation.user.annotations.PasswordsValueMatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValueMatchValidator
        implements ConstraintValidator<PasswordsValueMatchConstraint, Object> {


    public void initialize(PasswordsValueMatchConstraint constraintAnnotation) {
    }

    public boolean isValid(Object obj,
                           ConstraintValidatorContext context) {

        UserRegisterDto userRegisterDto = (UserRegisterDto) obj;

        String fieldValue = userRegisterDto.getPassword();

        String fieldMatchValue = userRegisterDto.getRepeatPassword();

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
