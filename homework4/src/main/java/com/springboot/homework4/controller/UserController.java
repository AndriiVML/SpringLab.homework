package com.springboot.homework4.controller;

import com.springboot.homework4.dto.UserDto;
import com.springboot.homework4.dto.UserRegisterDto;
import com.springboot.homework4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController has @ResponseBody - that says UserDto will be automatically parsed to JSON
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //injects by a type
    private final UserService userService;


//    @PatchMapping(value = "/{id}")
//    //@RequestBody int discount => in JSON just input primitive like: 100, not "discount": "100"
//    public UserDto updateUserDiscount(@PathVariable long id, @RequestBody int discount) {
//        return userService.updateUserDiscount(id, discount);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(){
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
        return userService.updateWholeUser(login, userDto);
    }

    @PatchMapping(value = "/{login}")
    public UserDto changeUserBlockStatus(@PathVariable String login) {
        return userService.changeUserBlockStatus(login);
    }

    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();//204 deleted successfully
    }





}
