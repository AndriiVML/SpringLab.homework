package com.springboot.homework4.repository.impl;

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
    public User getUserByLogin(String login) {
        return list.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found account by login  = " + login));
    }

    @Override
    public List<User> getAllUsers() {
        return list;
    }

    @Override
    public User createUser(User user) {
        if (list.contains(user)) {
            throw new RuntimeException("User is already created.");
        }
        try {
            createAccount(user);
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
        }
        list.add(user);
        return user;
    }

    private Account createAccount(Account account) throws RuntimeException {
        boolean accountExists = list.stream().anyMatch(u -> u.getLogin().equals(account.getLogin()));
        if (accountExists) {
            throw new RuntimeException("account with login " + account.getLogin() + " has already created");
        }
        return account;
    }


    @Override
    public User updateWholeUser(String login, User user) {
        boolean isDeleted = list.removeIf(u -> login.equals(user.getLogin()));

        if (isDeleted) {
            list.add(user);

        } else {
            throw new RuntimeException("User does not exist.");
        }

        return user;
    }



    @Override
    public void deleteUser(String login) {
        list.removeIf(user -> user.getLogin().equals(login));
    }

//    @Override
//    public User updateUserDiscount(long id, int discount) {
//        User user = list.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Not found user by id = " + id));
//        user.setDiscount(discount);
//        return user;
//    }

    @Override
    public User changeUserBlockStatus(String login) {
        User user = list.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found user by login = " + login));
        boolean isBlocked = user.isBlocked();

        user.setBlocked(!isBlocked);

        return user;
    }


}
