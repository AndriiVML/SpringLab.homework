package com.springboot.homework4.repository.impl;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.model.entity.Account;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class UserRepositoryImpl implements UserRepository {
    private List<User> list = new ArrayList<>();


    @Override
    public User getUser(String login) {
        return list.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found account with login = " + login));
    }

    @Override
    public List<User> getAllUsers() {
        return list;
    }

    @Override
    public User createUser(User user) {
        boolean userExists=list.stream().anyMatch(u-> u.getLogin().equals(user.getLogin()));
        if (userExists) {
            log.error(String.format("Unsuccessful attempt to create user. User's already created: %s", user));
            throw new RuntimeException("User's already created.");
        }
        try {
            createAccount(user);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        list.add(user);
        return user;
    }

    private Account createAccount(Account account) throws RuntimeException {
        boolean accountExists = list.stream().anyMatch(a -> a.getLogin().equals(account.getLogin()));
        if (accountExists) {
            log.error(
                    String.format("Unsuccessful attempt to create account. " +
                                    "Account with login %s has already created",
                            account.getLogin()));
            throw new RuntimeException("account with login " + account.getLogin() + " has already created");
        }
        return account;
    }


    @Override
    public User updateUser(String login, User user) {
        boolean isDeleted = list.removeIf(u -> login.equals(user.getLogin()));

        if (isDeleted) {
            list.add(user);

        } else {
            log.error(String.format("Cannot update user. User does not exists %s", user));
            throw new RuntimeException("User does not exist.");
        }

        return user;
    }


    @Override
    public void deleteUser(String login) {
        list.removeIf(user -> user.getLogin().equals(login));
    }



}
