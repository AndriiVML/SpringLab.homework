package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.validation.annotations.PasswordsValueMatchConstraint;
import com.mlav.springboot.travelagency.validation.user.*;
import com.mlav.springboot.travelagency.validation.annotations.EmailUniqueConstraint;
import com.mlav.springboot.travelagency.validation.annotations.LoginUniqueConstraint;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@PasswordsValueMatchConstraint(message = "Passwords do not match",
        groups = {UserRegister.class, UserCustomAnnotations.class})
public class UserDto {
    private Long id;

    @LoginUniqueConstraint(groups = {UserRegister.class, UserDefaultAnnotations.class,
            UserPatchUpdate.class, UserPutUpdate.class})
    @NotNull(message = "Login is a mandatory field", groups = {UserDefaultAnnotations.class, UserPutUpdate.class})
    private String login;

    @NotNull(message = "First Name is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class, UserPutUpdate.class})
    private String firstName;

    @NotNull(message = "Last Name is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class, UserPutUpdate.class})
    private String lastName;

    @Email(groups = {UserRegister.class,UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @EmailUniqueConstraint(groups = {UserRegister.class,UserDefaultAnnotations.class,
            UserPatchUpdate.class, UserPutUpdate.class})
    @NotNull(message = "Email is a mandatory field",
            groups = {UserRegister.class,UserDefaultAnnotations.class, UserPutUpdate.class})
    private String email;

    @NotNull(message = "Password is a mandatory field",
            groups = {UserRegister.class,UserDefaultAnnotations.class, UserPutUpdate.class})
    private String password;

    @NotNull(message = "Repeat password is a mandatory field",
            groups = {UserRegister.class,UserDefaultAnnotations.class})
    private String repeatPassword;

    @Null(message = "You don't have permission to change discount",
            groups = {UserPatchUpdate.class, UserPutUpdate.class})
    private Integer discount;
    @Null(message = "You don't have permission to change isBlocked status",
            groups = {UserPatchUpdate.class, UserPutUpdate.class})
    private Boolean isBlocked;


}
