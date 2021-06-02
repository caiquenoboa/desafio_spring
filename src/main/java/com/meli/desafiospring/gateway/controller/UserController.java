package com.meli.desafiospring.gateway.controller;

import com.meli.desafiospring.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> followUser(@PathVariable(value = "userId") Integer userId,
                                                   @PathVariable(value = "userIdToFollow") Integer userIdToFollow){

        userService.followUser(userId, userIdToFollow);

        return new ResponseEntity(HttpStatus.OK);
    }
}
