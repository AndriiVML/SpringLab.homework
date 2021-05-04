package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.validation.annotations.PasswordsValueMatchConstraint;
import com.mlav.springboot.travelagency.validation.user.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@PasswordsValueMatchConstraint(message = "Passwords do not match",
        groups = {UserRegister.class, UserCustomAnnotations.class})
public class UserDto {
    private Long id;

    @NotNull(message = "Login is a mandatory field", groups = {UserDefaultAnnotations.class, UserPutUpdate.class})
    @Pattern(regexp = "\\w+", message = "Login should include only latin letters, arabic numerals and '_'"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(min = 3, message = "Too short name of login. Login should have at least 3 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(max = 30, message = "Too long name of login. Login cannot exceed 30 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    private String login;

    @NotNull(message = "First Name is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class, UserPutUpdate.class})
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "First name should have only latin letters and start with upper-case letter"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(min = 3, message = "Too short first name. First name should have at least 3 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(max = 30, message = "Too long name of login. First name cannot exceed 30 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    private String firstName;

    @NotNull(message = "Last Name is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class, UserPutUpdate.class})
    @Pattern(regexp = "[A-Z][a-z]+(-[A-Z][a-z]+)?",
            message = "Last name should have only latin letters and start with upper-case letter. " +
                    "If last name is double then first and second parts separate by '-'. " +
                    "The second part follows the same rules as the first"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(min = 3, message = "Too short first name. Last name should have at least 3 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(max = 30, message = "Too long name of login. Last name cannot exceed 30 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    private String lastName;

    @NotNull(message = "Email is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class, UserPutUpdate.class})
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            , message = "Email must contain @; before @ any latin letters, numbers and '+','_','.','-'; " +
            "after @ any latin letters, numbers and '.','-'."
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(min = 3, message = "Too short for email. Email should have at least 3 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(max = 50, message = "Too long for email. Email cannot exceed 50 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    private String email;

    @NotNull(message = "Password is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class, UserPutUpdate.class})
    @Size(min = 3, message = "Too short for password. Password should have at least 3 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    @Size(max = 30, message = "Too long for password. Password cannot exceed 30 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class, UserPatchUpdate.class, UserPutUpdate.class})
    private String password;

    @NotNull(message = "Repeat password is a mandatory field",
            groups = {UserRegister.class, UserDefaultAnnotations.class})
    @Size(min = 3, message = "Too short for password. Password should have at least 3 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class})
    @Size(max = 30, message = "Too long for password. Password cannot exceed 30 symbols"
            , groups = {UserRegister.class, UserDefaultAnnotations.class})
    private String repeatPassword;

    @Null(message = "You don't have permission to change discount",
            groups = {UserPatchUpdate.class, UserPutUpdate.class})
    private Integer discount;
    @Null(message = "You don't have permission to change isBlocked status",
            groups = {UserPatchUpdate.class, UserPutUpdate.class})
    private Boolean isBlocked;


}
