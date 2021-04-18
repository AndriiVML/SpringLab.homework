package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.dto.UserRegisterDto;
import com.mlav.springboot.travelagency.model.entity.Discount;

import java.util.List;

public interface UserService {

    UserDto getUserByLogin(String login);

    List<UserDto> getAllUsers();

    UserRegisterDto createUser(UserRegisterDto userRegisterDto);


    void deleteUser(String login);


    UserDto updateUser(String login, UserDto userDto);


}
