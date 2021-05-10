package com.mlav.springboot.travelagency.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mlav.springboot.travelagency.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


/*
* Can split this model into two, one for user another for admin
* */

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {
    @JsonUnwrapped
    //userDto go upper in serialization no nesting
    private UserDto userDto;
}
