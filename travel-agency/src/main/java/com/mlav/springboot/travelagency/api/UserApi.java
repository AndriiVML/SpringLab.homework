package com.mlav.springboot.travelagency.api;

import com.mlav.springboot.travelagency.controller.model.UserModel;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.validation.user.UserPatchUpdate;
import com.mlav.springboot.travelagency.validation.user.UserPutUpdate;
import com.mlav.springboot.travelagency.validation.user.UserRegister;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {
    @ApiOperation("Get user from database")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserModel> getAllUsers();

    @ApiOperation("Get user from database")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    UserModel getUser(@PathVariable String login);

    @ApiOperation("Create and add user to the database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@Validated(UserRegister.class) @RequestBody UserDto userDto);

    @ApiOperation("Update user(put) in database(fields:\"login\",\"email\"  must be new and unique)")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    UserModel updateUser(@PathVariable String login,
                         @Validated(UserPutUpdate.class) @RequestBody UserDto userDto);

    @ApiOperation("Update user(patch) in database")
    @PatchMapping(value = "/{login}")
    UserModel applyPatchToUser(@PathVariable String login,
                               @Validated(UserPatchUpdate.class) @RequestBody UserDto userDto);

    @ApiOperation("Change block status of user in database")
    @PatchMapping(value = "/{login}/change-block-status")
    UserModel changeBlockStatus(@PathVariable String login);

    @ApiOperation("Delete user from database")
    @DeleteMapping(value = "/{login}")
    ResponseEntity<Void> deleteUser(@PathVariable String login);
}
