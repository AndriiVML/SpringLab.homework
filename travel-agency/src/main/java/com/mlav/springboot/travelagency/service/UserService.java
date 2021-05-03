package com.mlav.springboot.travelagency.service;

import com.mlav.springboot.travelagency.dto.UserDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    UserDto getUserByLogin(String login);

    List<UserDto> getAllUsers();

    List<UserDto> findPaginated(int pageNumber, int pageSize, List<Sort.Order> orders);

    UserDto createUser(UserDto userDto);

    void deleteUser(String login);


    UserDto updateUser(String login, UserDto userDto);


}
