package com.mlav.springboot.travelagency.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mlav.springboot.travelagency.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {
    @JsonUnwrapped
    //userDto go upper in serialization no nesting
    private UserDto userDto;
}
