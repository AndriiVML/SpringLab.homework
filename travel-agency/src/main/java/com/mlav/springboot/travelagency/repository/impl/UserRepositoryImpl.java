package com.mlav.springboot.travelagency.repository.impl;

import com.mlav.springboot.travelagency.exception.AccountNotFoundException;
import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.model.entity.Account;
import com.mlav.springboot.travelagency.model.entity.User;
import com.mlav.springboot.travelagency.repository.UserRepository;
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
                .orElseThrow(UserNotFoundException::new);
        log.info("user: " + user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = list.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
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
        createAccount(user);
        list.add(user);
        log.info("New user:" + user);
        return user;
    }

    private Account createAccount(Account account) {
        boolean accountExists = list.stream().anyMatch(a -> a.getLogin().equals(account.getLogin()));
        if (accountExists) {
            throw new AccountNotFoundException();
        }
        accounts.add(account);
        log.info("New account:" + account);
        return account;
    }

    private void deleteAccount(String login) {
        boolean isDeleted = accounts.removeIf(acc -> acc.getLogin().equals(login));
        if (!isDeleted) {
            String message = "Cannot delete a non-existent account";
            throw new AccountNotFoundException(message);
        }
        log.info("Account is deleted");
    }


    @Override
    public User updateUser(String login, User user) {
        boolean isDeleted = list.removeIf(u -> login.equals(u.getLogin()));

        if (isDeleted) {
            list.add(user);
        } else {
            throw new UserNotFoundException("Cannot update user. User is not found!");
        }

        log.info("User is updated");

        return user;
    }


    @Override
    public void deleteUser(String login) {
        boolean isDeleted = list.removeIf(user -> user.getLogin().equals(login));
        if (!isDeleted) {
            String message = "Cannot delete a non-existent user";
            throw new UserNotFoundException(message);
        }
        log.info("User is deleted");
        log.info("Attempt to delete account with login: " + login);
        deleteAccount(login);
    }


}
