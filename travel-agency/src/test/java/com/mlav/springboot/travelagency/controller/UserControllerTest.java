package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.controller.assembler.UserAssembler;
import com.mlav.springboot.travelagency.controller.model.UserModel;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.service.UserService;
import com.mlav.springboot.travelagency.test.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static com.mlav.springboot.travelagency.test.util.TestDataUtil.TEST_LOGIN;
import static com.mlav.springboot.travelagency.test.util.TestDataUtil.createTestUserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

    @Test
    void getAllUsers() {
    }

    @Test
    void getPaginatedAndSorted() {
    }

    @Test
    void getUser() throws Exception {
        UserDto userDto = createTestUserDto();
        UserModel userModel = new UserModel(userDto);
        when(userService.getUserByLogin(TEST_LOGIN)).thenReturn(userDto);
        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + TEST_LOGIN))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value(TEST_LOGIN));
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void applyPatchToUser() {
    }

    @Test
    void changeBlockStatus() {
    }

    @Test
    void deleteUser() {
    }
}