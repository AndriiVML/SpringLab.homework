package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.dto.UserRegisterDto;
import com.mlav.springboot.travelagency.model.entity.Discount;
import com.mlav.springboot.travelagency.service.UserService;
import com.mlav.springboot.travelagency.validation.user.UserPatchUpdate;
import com.mlav.springboot.travelagency.validation.user.UserPutUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
//@RestController has @ResponseBody - that says UserDto will be automatically parsed to JSON
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //injects by a type
    private final UserService userService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        log.info("Attempt to get all users");
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public UserDto getUser(@PathVariable String login) {
        log.info("Attempt to get user with login=" + login);
        return userService.getUserByLogin(login);
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
    public UserRegisterDto createUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        log.info("Attempt to create user: " + userRegisterDto);
        return userService.createUser(userRegisterDto);
    }

    /*
    * Works fine when login and email is new and unique
    * */

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public UserDto updateUser(@PathVariable String login,
                              @Validated(UserPutUpdate.class) @RequestBody UserDto userDto) {
        log.info(String.format("Attempt to update user with login=%s possibleUpdate: %s", login, userDto));
        UserDto userFromDb = userService.getUserByLogin(login);
        userDto.setId(userFromDb.getId());
        userDto.setDiscount(userFromDb.getDiscount());
        userDto.setIsBlocked(userFromDb.getIsBlocked());
        return userService.updateUser(login, userDto);
    }


    @PatchMapping(value = "/{login}")
    public UserDto applyPatchToUser(@PathVariable String login,
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
        return userService.updateUser(login, userDto);
    }


    @PatchMapping(value = "/{login}/change-block-status")
    public UserDto changeBlockStatus(@PathVariable String login) {
        log.info("Attempt to change block-status of user with login=" + login);
        UserDto userDto = userService.getUserByLogin(login);
        userDto.setIsBlocked(!userDto.getIsBlocked());
        return userService.updateUser(login, userDto);
    }


    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.info("Attempt to delete user with login=" + login);
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();//204 deleted successfully
    }


}
