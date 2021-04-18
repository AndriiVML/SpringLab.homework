package com.mlav.springboot.travelagency.dto;

import com.mlav.springboot.travelagency.validation.user.UserPatchUpdate;
import com.mlav.springboot.travelagency.validation.user.UserPutUpdate;
import com.mlav.springboot.travelagency.validation.user.annotations.EmailUniqueConstraint;
import com.mlav.springboot.travelagency.validation.user.annotations.LoginUniqueConstraint;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {
    private Long id;
    @LoginUniqueConstraint(groups = {UserPatchUpdate.class, UserPutUpdate.class})
    @NotNull(message = "Login is a mandatory field", groups = UserPutUpdate.class)
    private String login;
    @NotNull(message = "First Name is a mandatory field",groups = UserPutUpdate.class)
    private String firstName;
    @NotNull(message = "Last Name is a mandatory field",groups = UserPutUpdate.class)
    private String lastName;
    @Email(groups = {UserPatchUpdate.class, UserPutUpdate.class})
    @EmailUniqueConstraint(groups = {UserPatchUpdate.class, UserPutUpdate.class})
    @NotNull(message = "Email is a mandatory field",groups = UserPutUpdate.class)
    private String email;
    @NotNull(message = "Password is a mandatory field",groups = UserPutUpdate.class)
    private String password;
    @Null(message = "You don't have permission to change discount",
            groups = {UserPatchUpdate.class, UserPutUpdate.class})
    private Integer discount;
    @Null(message = "You don't have permission to change isBlocked status",
            groups = {UserPatchUpdate.class, UserPutUpdate.class})
    private Boolean isBlocked;

}
