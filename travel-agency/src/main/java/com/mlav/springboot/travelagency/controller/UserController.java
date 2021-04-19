package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.controller.assembler.UserAssembler;
import com.mlav.springboot.travelagency.controller.model.UserModel;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.service.UserService;
import com.mlav.springboot.travelagency.util.Util;
import com.mlav.springboot.travelagency.validation.user.UserPatchUpdate;
import com.mlav.springboot.travelagency.validation.user.UserPutUpdate;
import com.mlav.springboot.travelagency.validation.user.UserRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RestController has @ResponseBody - that says UserDto will be automatically parsed to JSON
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //injects by a type
    private final UserService userService;
    private final UserAssembler userAssembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> getAllUsers() {
        log.info("Attempt to get all users");
        List<UserDto> allUsers = userService.getAllUsers();
        return mapListUserDtoToListUserModel(allUsers);
    }

    private List<UserModel> mapListUserDtoToListUserModel(List<UserDto> userDtos) {
        List<UserModel> userModels = new ArrayList<>(userDtos.size());
        for (UserDto item : userDtos) {
            userModels.add(userAssembler.toModel(item));
        }
        return userModels;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public UserModel getUser(@PathVariable String login) {
        log.info("Attempt to get user with login=" + login);
        UserDto userDto = userService.getUserByLogin(login);
        return userAssembler.toModel(userDto);
    }


    //test in postman
    /*
        {
            "login":"newLogin",
            "firstName":"firstName1",
            "lastName":"lastName1",
            "email":"email@email.com",
            "password":"pass1",
            "repeatPassword":"pass1"
        }
    */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserModel createUser(@Validated(UserRegister.class) @RequestBody UserDto userDto) {
        log.info("Attempt to create user: " + userDto);
        userDto.setId(Util.generateUniqueId());
        userDto.setDiscount(0);
        userDto.setIsBlocked(false);
        UserDto entity = userService.createUser(userDto);
        return userAssembler.toModel(entity);
    }

    /*
     * Works fine when login and email is new and unique
     * */

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public UserModel updateUser(@PathVariable String login,
                                @Validated(UserPutUpdate.class) @RequestBody UserDto userDto) {
        log.info(String.format("Attempt to update user with login=%s possibleUpdate: %s", login, userDto));
        UserDto userFromDb = userService.getUserByLogin(login);
        userDto.setId(userFromDb.getId());
        userDto.setDiscount(userFromDb.getDiscount());
        userDto.setIsBlocked(userFromDb.getIsBlocked());
        UserDto entity = userService.updateUser(login, userDto);
        return userAssembler.toModel(entity);
    }


    @PatchMapping(value = "/{login}")
    public UserModel applyPatchToUser(@PathVariable String login,
                                      @Validated(UserPatchUpdate.class) @RequestBody UserDto userDto) {
        log.info(String.format("Attempt to update user with login=%s on user: %s", login, userDto));
        UserDto userFromDb = userService.getUserByLogin(login);
        if (userDto.getLogin() == null) {
            userDto.setLogin(userFromDb.getLogin());
        }
        if (userDto.getEmail() == null) {
            userDto.setEmail(userFromDb.getEmail());
        }
        if (userDto.getPassword() == null) {
            userDto.setPassword(userFromDb.getPassword());
        }
        if (userDto.getFirstName() == null) {
            userDto.setFirstName(userFromDb.getFirstName());
        }
        if (userDto.getLastName() == null) {
            userDto.setLastName(userFromDb.getLastName());
        }
        userDto.setId(userFromDb.getId());
        userDto.setDiscount(userFromDb.getDiscount());
        userDto.setIsBlocked(userFromDb.getIsBlocked());
        UserDto entity = userService.updateUser(login, userDto);
        return userAssembler.toModel(entity);
    }


    @PatchMapping(value = "/{login}/change-block-status")
    public UserModel changeBlockStatus(@PathVariable String login) {
        log.info("Attempt to change block-status of user with login=" + login);
        UserDto userDto = userService.getUserByLogin(login);
        userDto.setIsBlocked(!userDto.getIsBlocked());
        UserDto entity = userService.updateUser(login, userDto);
        return userAssembler.toModel(entity);
    }


    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.info("Attempt to delete user with login=" + login);
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();//204 deleted successfully
    }


}
