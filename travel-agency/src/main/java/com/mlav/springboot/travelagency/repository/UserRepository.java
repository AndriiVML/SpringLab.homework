package com.mlav.springboot.travelagency.repository;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.model.entity.User;

import java.util.List;

public interface UserRepository {
    int getCount();

    User getUser(String login);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(String login, User user);

    void deleteUser(String login);


}
