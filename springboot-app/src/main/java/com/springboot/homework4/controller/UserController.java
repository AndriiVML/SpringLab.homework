package com.springboot.homework4.controller;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;
import com.springboot.homework4.model.entity.Discount;
import com.springboot.homework4.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
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

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public UserDto updateUser(@PathVariable String login, @Valid @RequestBody UserDto userDto) {
        log.info(String.format("Attempt to update user with login=%s possibleUpdate: %s", login, userDto));
        userDto.setId(userService.getUserByLogin(login).getId());
        return userService.updateUser(login, userDto);
    }


    //maybe validated here
    @PatchMapping(value = "/{login}")
    public UserDto applyPatchToUser(@PathVariable String login, @RequestBody Map<Object, Object> fields) {
        log.info(String.format("Attempt to update user with login=%s updateFields: %s", login, fields));
       @Valid final UserDto userDto = userService.getUserByLogin(login);
        fields.forEach((k, v) -> {
            if (!(k.equals("firstName") || k.equals("lastName") || k.equals("password"))) {
                String message = "this field is not allowed: " + k;
                log.error(message);
                throw new RuntimeException(message);
            }
            Field field = ReflectionUtils.findField(UserDto.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, userDto, v);
        });
        return userService.updateUser(login, userDto);
    }


    @PatchMapping(value = "/{login}/change-block-status")
    public UserDto changeBlockStatus(@PathVariable String login) {
        log.info("Attempt to change block-status of user with login=" + login);
        UserDto userDto = userService.getUserByLogin(login);
        userDto.setBlocked(!userDto.isBlocked());
        return userService.updateUser(login, userDto);
    }


    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.info("Attempt to delete user with login=" + login);
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();//204 deleted successfully
    }

    @GetMapping("/discount")
    public Discount getDiscount() {
        log.info("Attempt to get general discount");
        return userService.getDiscount();
    }

    @PatchMapping("/discount")
    public Discount updateDiscount(@RequestBody Map<String, Integer> json) {
        log.info("Attempt to change discount fields on " + json);
        Integer step = json.get("step");
        Integer max = json.get("max");
        if (step == null) {
            step = userService.getDiscount().getStep();
        }
        if (max == null) {
            max = userService.getDiscount().getMax();
        }
        if(step==userService.getDiscount().getStep()&&max==userService.getDiscount().getMax()){
            log.info("Nothing has changed in discount");
            return userService.getDiscount();
        }
        return userService.setDiscount(step, max);
    }

}
