package com.springboot.homework4.service;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;

import java.util.List;

public interface UserService {

    UserDto getUserByLogin(String login);

    List<UserDto> getAllUsers();

    UserRegisterDto createUser(UserRegisterDto userRegisterDto);


    void deleteUser(String login);


    UserDto updateUser(String login, UserDto userDto);


}
