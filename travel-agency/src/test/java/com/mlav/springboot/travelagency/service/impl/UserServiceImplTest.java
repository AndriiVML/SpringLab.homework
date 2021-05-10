package com.mlav.springboot.travelagency.service.impl;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.entity.User;
import com.mlav.springboot.travelagency.repository.UserRepository;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mlav.springboot.travelagency.test.util.TestDataUtil.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void getUserByLogin() {
        User user = createTestUser();
        when(userRepository.findByAccount_Login(TEST_LOGIN)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUserByLogin(TEST_LOGIN);

        assertThat(userDto, allOf(
                hasProperty("login", equalTo(user.getAccount().getLogin())),
                hasProperty("password", equalTo(user.getAccount().getPassword())),
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstName", equalTo(user.getFirstName())),
                hasProperty("lastName", equalTo(user.getLastName())),
                hasProperty("discount", is(comparesEqualTo(user.getDiscount()))),
                hasProperty("isBlocked", equalTo(user.getIsBlocked()))
        ));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void findPaginated() {
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}