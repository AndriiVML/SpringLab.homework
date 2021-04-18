package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.entity.User;

import java.util.List;

public interface UserRepository {
    int getCount();

    User getUser(String login);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(String login, User user);

    void deleteUser(String login);


}
