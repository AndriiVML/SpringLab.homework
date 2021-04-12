package com.springboot.homework4.controller;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;
import com.springboot.homework4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
//@RestController has @ResponseBody - that says UserDto will be automatically parsed to JSON
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //injects by a type
    private final UserService userService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public UserDto getUser(@PathVariable String login) {
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
    public UserRegisterDto createUser(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.createUser(userRegisterDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UserDto userDto) {
        userDto.setId(userService.getUserByLogin(login).getId());
        return userService.updateUser(login, userDto);
    }


    @PatchMapping(value = "/{login}")
    public UserDto applyPatchToUser(@PathVariable String login, @RequestBody Map<Object, Object> fields) {
        final UserDto userDto = userService.getUserByLogin(login);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(UserDto.class, (String) k);
            field.setAccessible(true);
            if (k.equals("discount")) {
                ReflectionUtils.setField(field, userDto, Integer.parseInt((String) v));
                return;//only skips this iteration
            }
            if (k.equals("isBlocked")) {
                ReflectionUtils.setField(field, userDto, Boolean.parseBoolean((String) v));
                return;//only skips this iteration
            }

            ReflectionUtils.setField(field, userDto, v);
        });
        return userService.updateUser(login, userDto);
    }


    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();//204 deleted successfully
    }


}
