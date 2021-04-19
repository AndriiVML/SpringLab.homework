package com.mlav.springboot.travelagency.service.impl;

import com.mlav.springboot.travelagency.repository.DiscountRepository;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.entity.User;
import com.mlav.springboot.travelagency.repository.UserRepository;
import com.mlav.springboot.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
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
                .discount(user.getDiscount())
                .email(user.getEmail())
                .isBlocked(user.isBlocked())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .isBlocked(userDto.getIsBlocked())
                .build();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        return user;
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
    public UserDto createUser(UserDto userDto) {
        log.info("Attempt to create user: " + userDto);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);

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
