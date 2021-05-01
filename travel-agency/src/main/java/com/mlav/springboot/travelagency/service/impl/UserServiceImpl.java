package com.mlav.springboot.travelagency.service.impl;

import com.mlav.springboot.travelagency.exception.AccountNotFoundException;
import com.mlav.springboot.travelagency.exception.UserNotFoundException;
import com.mlav.springboot.travelagency.model.Role;
import com.mlav.springboot.travelagency.model.entity.Account;
import com.mlav.springboot.travelagency.repository.AccountRepository;
import com.mlav.springboot.travelagency.repository.DiscountRepository;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.entity.User;
import com.mlav.springboot.travelagency.repository.UserRepository;
import com.mlav.springboot.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getAccount().getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .discount(user.getDiscount())
                .email(user.getEmail())
                .isBlocked(user.getIsBlocked())
                .password(user.getAccount().getPassword())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .discount(userDto.getDiscount())
                .isBlocked(userDto.getIsBlocked())
                .build();
        Account account = Account.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .roleId(Role.USER.ordinal())
                .build();
        user.setAccount(account);
        return user;
    }


    @Override
    public UserDto getUserByLogin(String login) {
        log.info("Attempt to get user with login=" + login);
        User user = userRepository.findByAccount_Login(login).orElseThrow(UserNotFoundException::new);
        log.info("user: {}", user);
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Attempt to get all users");
        List<User> users = userRepository.findAll();
        log.info("all users: {}", users);
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users) {
            userDtos.add(mapUserToUserDto(u));
        }
        return userDtos;
    }

    @Override
    public List<UserDto> findPaginated(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : userRepository.findAll(paging).toList()) {
            userDtos.add(mapUserToUserDto(u));
        }
        return userDtos;
    }


    @Transactional(rollbackOn = {SQLException.class})
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapUserDtoToUser(userDto);
        Account account = user.getAccount();
        account.setRoleId(Role.USER.ordinal());
        log.info("Attempt to create account: " + account);
        account = accountRepository.save(account);
        log.info("New account: " + account);
        user.setAccount(account);
        user.setId(account.getId());
        user.setDiscount(0);
        user.setIsBlocked(false);
        log.info("Attempt to create user: " + user);
        user = userRepository.save(user);
        log.info("New user: " + user);
        return mapUserToUserDto(user);

    }

    @Transactional(rollbackOn = {SQLException.class})
    @Override
    public void deleteUser(String login) {
        log.info("Attempt to delete user with login=" + login);
        User user = userRepository.findByAccount_Login(login)
                .orElseThrow(() -> new UserNotFoundException("Cannot delete a non-existent user"));
        userRepository.delete(user);
        log.info("User and account are deleted");
    }


    @Transactional(rollbackOn = {SQLException.class})
    @Override
    public UserDto updateUser(String login, UserDto userDto) {
        log.info(String.format("Attempt to update user with login=%s possibleUpdate: %s", login, userDto));
        User user = mapUserDtoToUser(userDto);
        User userFromDb = userRepository.findByAccount_Login(login)
                .orElseThrow(() -> new UserNotFoundException("Cannot update user. User does not exist"));
        //automatically sets account id
        user.setId(userFromDb.getId());
//        accountRepository.save(user.getAccount());
//        log.info("Account is updated");

        //updates account too
        user = userRepository.save(user);
        log.info("User and account is updated");
        return mapUserToUserDto(user);
    }


}
