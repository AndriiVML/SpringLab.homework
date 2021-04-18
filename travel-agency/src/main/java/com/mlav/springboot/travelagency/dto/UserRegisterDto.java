package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.validation.user.UserCustomAnnotations;
import com.mlav.springboot.travelagency.validation.user.UserDefaultAnnotations;
import com.mlav.springboot.travelagency.validation.user.annotations.EmailUniqueConstraint;
import com.mlav.springboot.travelagency.validation.user.annotations.LoginUniqueConstraint;
import com.mlav.springboot.travelagency.validation.user.annotations.PasswordsValueMatchConstraint;
import lombok.Builder;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@GroupSequence({UserRegisterDto.class, UserDefaultAnnotations.class, UserCustomAnnotations.class})
@PasswordsValueMatchConstraint(message = "Passwords do not match", groups = UserCustomAnnotations.class)
public class UserRegisterDto {
    private Long id;
    @NotNull(message = "Login is a mandatory field", groups = UserDefaultAnnotations.class)
    @LoginUniqueConstraint(message = "Login should be unique", groups = UserDefaultAnnotations.class)
    private String login;
    @NotNull(message = "First name is a mandatory field", groups = UserDefaultAnnotations.class)
    private String firstName;
    @NotNull(message = "Last name is a mandatory field", groups = UserDefaultAnnotations.class)
    private String lastName;
    @Email(groups = UserDefaultAnnotations.class)
    @EmailUniqueConstraint(groups = UserDefaultAnnotations.class)
    @NotNull(message = "Email is a mandatory field", groups = UserDefaultAnnotations.class)
    private String email;
    @NotNull(message = "Password is a mandatory field", groups = UserDefaultAnnotations.class)
    private String password;
    @NotNull(message = "Repeat password is a mandatory field", groups = UserDefaultAnnotations.class)
    private String repeatPassword;
}
