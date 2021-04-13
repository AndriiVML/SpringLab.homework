package com.springboot.homework4.service.impl;

import com.springboot.homework4.model.entity.Discount;
import com.springboot.homework4.repository.DiscountRepository;
import com.springboot.homework4.util.Util;
import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.UserRepository;
import com.springboot.homework4.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;


    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .isBlocked(user.isBlocked())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .discount(userDto.getDiscount())
                .isBlocked(userDto.isBlocked())
                .build();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserRegisterDto mapUserToUserRegisterDto(User user) {
        return UserRegisterDto.builder()
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

    private User mapUserRegisterDtoToUser(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .email(userRegisterDto.getEmail())
                .build();
        user.setId(Util.generateUniqueId());
        user.setLogin(userRegisterDto.getLogin());
        user.setPassword(userRegisterDto.getPassword());
        return user;
    }


    @Override
    public Discount setDiscount(int step, int max) {
        log.info("Attempt to set general discount: step={}, max={}", step, max);
        return discountRepository.updateDiscount(step, max);
    }

    @Override
    public Discount getDiscount() {
        log.info("Attempt to get general discount");
        return discountRepository.getDiscount();
    }

    @Override
    public UserDto getUserByLogin(String login) {
        log.info("Attempt to get user with login=" + login);
        User user = userRepository.getUser(login);
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Attempt to get all users");
        List<User> users = userRepository.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users) {
            userDtos.add(mapUserToUserDto(u));
        }
        return userDtos;
    }


    @Override
    public UserRegisterDto createUser(UserRegisterDto userRegisterDto) {
        log.info("Attempt to create user: " + userRegisterDto);
        User user = mapUserRegisterDtoToUser(userRegisterDto);
        user = userRepository.createUser(user);
        return mapUserToUserRegisterDto(user);

    }


    @Override
    public void deleteUser(String login) {
        log.info("Attempt to delete user with login=" + login);
        userRepository.deleteUser(login);
    }


    @Override
    public UserDto updateUser(String login, UserDto userDto) {
        log.info(String.format("Attempt to update user with login=%s possibleUpdate: %s", login, userDto));
        User user = mapUserDtoToUser(userDto);
        user = userRepository.updateUser(login, user);
        return mapUserToUserDto(user);
    }


}
