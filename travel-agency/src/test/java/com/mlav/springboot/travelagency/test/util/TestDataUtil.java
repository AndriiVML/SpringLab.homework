package com.mlav.springboot.travelagency.test.util;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.model.Role;
import com.mlav.springboot.travelagency.model.entity.Account;
import com.mlav.springboot.travelagency.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {

    public static final String TEST_EMAIL_COM = "email@email.com";

    public static final String TEST_LOGIN = "login";

    public static final String TEST_FIRST_NAME = "Firstname";

    public static final String TEST_LAST_NAME = "Lastname";

    public static final String TEST_PASSWORD = "pass";

    public static final Integer TEST_DISCOUNT = 0;

    public static final Boolean TEST_IS_BLOCKED = false;

    public static User createTestUser() {
        User user = User.builder()
                .email(TEST_EMAIL_COM)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .discount(TEST_DISCOUNT)
                .isBlocked(TEST_IS_BLOCKED)
                .build();
        Account account = Account.builder()
                .login(TEST_LOGIN)
                .password(TEST_PASSWORD)
                .roleId(Role.USER.ordinal())
                .build();
        user.setAccount(account);
        return user;
    }

    public static UserDto createTestUserDto() {
        return UserDto.builder()
                .login(TEST_LOGIN)
                .password(TEST_PASSWORD)
                .email(TEST_EMAIL_COM)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .discount(TEST_DISCOUNT)
                .isBlocked(TEST_IS_BLOCKED)
                .build();
    }

}
