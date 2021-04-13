package com.springboot.homework4.repository.impl;

import com.springboot.homework4.model.entity.Account;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private List<Account> accounts = new ArrayList<>();
    private List<User> list = new ArrayList<>();


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User getUser(String login) {

        User user = list.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is not found"));
        log.info("user: " + user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("all users: " + list);
        return list;
    }

    @Override
    public User createUser(User user) {
        boolean userExists = list.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()));
        if (userExists) {
            String message = "User is already created";
            log.error(message);
            throw new RuntimeException(message);
        }
        try {
            createAccount(user);
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
        }
        list.add(user);
        log.info("New user:" + user);
        return user;
    }

    private Account createAccount(Account account) {
        boolean accountExists = list.stream().anyMatch(a -> a.getLogin().equals(account.getLogin()));
        if (accountExists) {
            String message = "Account is already created";
            log.error(message);
            throw new RuntimeException(message);
        }
        accounts.add(account);
        log.info("New account:" + account);
        return account;
    }

    private void deleteAccount(String login) {
        boolean isDeleted = accounts.removeIf(acc -> acc.getLogin().equals(login));
        if (!isDeleted) {
            String message = "Cannot delete a non-existent account";
            log.error(message);
            throw new RuntimeException(message);
        }
        log.info("Account is deleted");
    }


    @Override
    public User updateUser(String login, User user) {
        boolean isDeleted = list.removeIf(u -> login.equals(user.getLogin()));

        if (isDeleted) {
            list.add(user);

        } else {
            String message = "Cannot update user. Wrong login=" + user.getLogin();
            log.error(message);
            throw new RuntimeException(message);
        }

        log.info("User is updated");


        return user;
    }


    @Override
    public void deleteUser(String login) {
        boolean isDeleted = list.removeIf(user -> user.getLogin().equals(login));
        if (!isDeleted) {
            String message = "Cannot delete a non-existent user";
            log.error(message);
            throw new RuntimeException(message);
        }
        log.info("User is deleted");
        log.info("Attempt to delete account with login: " + login);
        deleteAccount(login);
    }


}
