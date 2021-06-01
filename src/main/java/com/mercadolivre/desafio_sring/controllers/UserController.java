package com.mercadolivre.desafio_sring.controllers;

import com.mercadolivre.desafio_sring.dtos.userDTOs.*;
import com.mercadolivre.desafio_sring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        UserCreateResponseDTO userCreateResponseDTO = userService.createUser(userCreateRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponseDTO);
    }

    @PostMapping(value = "{userId}/follow/{userIdToFollow}")
    public ResponseEntity<UserFollowersResponseDTO> createFollowUser(@PathVariable Long userId, @PathVariable Long userIdToFollow) {
        UserFollowersRequestDTO userFollowRequestDTO = new UserFollowersRequestDTO(userId, userIdToFollow);
        UserFollowersResponseDTO userFollowResponseDTO = userService.followUser(userFollowRequestDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}/followers/count")
    public ResponseEntity<UserFollowsCountResponseDTO> getFollowersCountUser(@PathVariable Long userId) {
        UserFollowsCountRequestDTO userFollowRequestDTO = new UserFollowsCountRequestDTO(userId);
        UserFollowsCountResponseDTO userFollowsCountResponseDTO = userService.followersCountUser(userFollowRequestDTO);

        return ResponseEntity.ok(userFollowsCountResponseDTO);
    }

    @GetMapping(value = "{userId}/followers/list")
    public ResponseEntity<UserFollowersListResponseDTO> getFollowersListUser(@PathVariable Long userId) {
        UserFollowersListRequestDTO userFollowersListRequestDTO = new UserFollowersListRequestDTO(userId);
        UserFollowersListResponseDTO userFollowersListResponseDTO = userService.followersListUser(userFollowersListRequestDTO);

        return ResponseEntity.ok(userFollowersListResponseDTO);
    }

    @GetMapping(value = "{userId}/followed/list")
    public ResponseEntity<UserFollowedListResponseDTO> getFollowedListUser(@PathVariable Long userId) {
        UserFollowedListRequestDTO userFollowedListRequestDTO = new UserFollowedListRequestDTO(userId);
        UserFollowedListResponseDTO userFollowedListResponseDTO = userService.followedListUser(userFollowedListRequestDTO);

        return ResponseEntity.ok(userFollowedListResponseDTO);
    }
}
