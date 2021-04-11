package com.springboot.homework4.repository;

import com.springboot.homework4.model.entity.User;

import java.util.List;

public interface UserRepository {
    User getUser(String login);

    List<User> getAllUsers();

    User createUser(User user);

    User updateWholeUser(String login, User user);

    void deleteUser(String email);

    User changeUserBlockStatus(String login);
}
