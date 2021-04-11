package com.springboot.homework4.service.impl;

import com.springboot.homework4.Util;
import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;
import com.springboot.homework4.model.entity.User;
import com.springboot.homework4.repository.UserRepository;
import com.springboot.homework4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .discount(user.getDiscount())
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
    public UserDto getUserByLogin(String login) {
        User user = userRepository.getUser(login);
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users) {
            userDtos.add(mapUserToUserDto(u));
        }
        return userDtos;
    }


    @Override
    public UserRegisterDto createUser(UserRegisterDto userRegisterDto) {
        User user = mapUserRegisterDtoToUser(userRegisterDto);
        user = userRepository.createUser(user);
        return mapUserToUserRegisterDto(user);

    }


    @Override
    public void deleteUser(String login) {
        userRepository.deleteUser(login);
    }


    @Override
    public UserDto updateWholeUser(String login, UserDto userDto) {
        User user = mapUserDtoToUser(userDto);
        user = userRepository.updateWholeUser(login, user);
        return mapUserToUserDto(user);
    }

//    @Override
//    public UserDto updateUserDiscount(long id, int discount) {
//        User user = userRepository.updateUserDiscount(id, discount);
//        return mapUserToUserDto(user);
//    }

    @Override
    public UserDto changeUserBlockStatus(String login) {
        User user = userRepository.changeUserBlockStatus(login);
        return mapUserToUserDto(user);
    }
}
