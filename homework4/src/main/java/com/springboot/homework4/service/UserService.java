package com.springboot.homework4.service;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;
import com.springboot.homework4.model.entity.Discount;

import java.util.List;

public interface UserService {

    Discount setDiscount(int step, int max);

    Discount getDiscount();

    UserDto getUserByLogin(String login);

    List<UserDto> getAllUsers();

    UserRegisterDto createUser(UserRegisterDto userRegisterDto);


    void deleteUser(String login);


    UserDto updateUser(String login, UserDto userDto);


}
