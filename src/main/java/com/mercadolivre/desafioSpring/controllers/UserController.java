package com.mercadolivre.desafioSpring.controllers;

import com.mercadolivre.desafioSpring.dtos.ResponseDTO;
import com.mercadolivre.desafioSpring.dtos.UserDTO;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.responses.UserResponse;
import com.mercadolivre.desafioSpring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        userService.followUser(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/{userId}/followers/count/")
    public ResponseEntity<UserResponse> getFollowersNumber(@PathVariable Integer userId) {
        UserResponse userResponse = userService.getFollowersNumber(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping(path = "/{userId}/followers/list/")
    public ResponseEntity<ResponseDTO> getFollowers(@PathVariable Integer userId) {
        ResponseDTO responseDTO = userService.getFollowers(userId);
        return ResponseEntity.status(responseDTO.getCode()).body(responseDTO);
    }
}
