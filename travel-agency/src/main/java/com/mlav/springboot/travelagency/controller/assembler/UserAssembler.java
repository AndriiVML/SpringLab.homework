package com.mlav.springboot.travelagency.controller.assembler;

import com.mlav.springboot.travelagency.controller.UserController;
import com.mlav.springboot.travelagency.controller.model.UserModel;
import com.mlav.springboot.travelagency.dto.UserDto;
import com.mlav.springboot.travelagency.util.Util;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel userModel = new UserModel(entity);
        Link get = linkTo(methodOn(UserController.class).
                getUser(entity.getLogin())).withRel("get");

        Link getAll = linkTo(methodOn(UserController.class)
                .getAllUsers())
                .withRel("getAll");
        Link getPaginated = linkTo(methodOn(UserController.class)
                .getPaginatedAndSorted(0, Util.PAGE_SIZE, new String[]{"id,desc"}))
                .withRel("getPaginated");
        Link create = linkTo(methodOn(UserController.class)
                .createUser(entity))
                .withRel("create");
        Link put = linkTo(methodOn(UserController.class)
                .updateUser(entity.getEmail(), entity))
                .withRel("put");
        Link patch = linkTo(methodOn(UserController.class)
                .applyPatchToUser(entity.getLogin(), entity))
                .withRel("patch");
        Link delete = linkTo(methodOn(UserController.class)
                .deleteUser(entity.getLogin()))
                .withRel("delete");
        Link changeBlockStatus = linkTo(methodOn(UserController.class)
                .changeBlockStatus(entity.getLogin()))
                .withRel("change-block-status");

        userModel.add(get, getPaginated, getAll, create, put, patch, delete, changeBlockStatus);
        return userModel;
    }
}
