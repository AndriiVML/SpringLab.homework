package com.mlav.springboot.travelagency.validation.validator;


import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.repository.UserRepository;
import com.mlav.springboot.travelagency.validation.annotations.LoginUniqueConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class LoginUniqueValidator implements
        ConstraintValidator<LoginUniqueConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(LoginUniqueConstraint loginUnique) {
    }

    @Override
    public boolean isValid(String login,
                           ConstraintValidatorContext cxt) {
        boolean isUnique = false;
        try {
            userRepository.getUser(login);
        } catch (UserNotFoundException ex) {
            isUnique = true;
        }
        return isUnique;
    }
}