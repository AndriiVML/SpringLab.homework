package com.mlav.springboot.travelagency.validation.order.validator;

import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.service.UserService;
import com.mlav.springboot.travelagency.validation.order.annotations.UserExistsConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UserExistsValidator
        implements ConstraintValidator<UserExistsConstraint, String> {

    private final UserService userService;


    public void initialize(UserExistsConstraint constraintAnnotation) {
    }

    public boolean isValid(String login,
                           ConstraintValidatorContext context) {

        boolean exists = true;
        try {
            userService.getUserByLogin(login);
        } catch (UserNotFoundException ex) {
            exists = false;
        }
        return exists;
    }
}
