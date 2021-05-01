package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserByLogin(String login);

    List<UserDto> getAllUsers();

    List<UserDto> findPaginated(int pageNumber, int pageSize);

    UserDto createUser(UserDto userDto);

    void deleteUser(String login);


    UserDto updateUser(String login, UserDto userDto);


}
