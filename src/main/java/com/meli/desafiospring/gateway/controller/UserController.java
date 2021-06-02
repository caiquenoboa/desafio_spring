package com.meli.desafiospring.gateway.controller;

import com.meli.desafiospring.gateway.request.UserRequest;
import com.meli.desafiospring.service.user.CreateUserService;
import com.meli.desafiospring.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final CreateUserService createUserService;

    @PostMapping(value = "/")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserRequest userRequest){
        createUserService.createUserByType(userRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> followUser(@PathVariable(value = "userId") Integer userId,
                                     @PathVariable(value = "userIdToFollow") Integer userIdToFollow){

        userService.followUser(userId, userIdToFollow);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
