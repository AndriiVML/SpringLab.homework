package com.mlav.springboot.travelagency.validation.validator;


import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.repository.UserRepository;
import com.mlav.springboot.travelagency.validation.annotations.EmailUniqueConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class EmailUniqueValidator implements
        ConstraintValidator<EmailUniqueConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailUniqueConstraint emailUnique) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {
        boolean isUnique = false;
        try {
            userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException ex) {
            isUnique = true;
        }
        return isUnique;
    }
}