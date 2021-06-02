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
    public ResponseEntity<String> createFollowUser(@PathVariable Long userId, @PathVariable Long userIdToFollow) {
        userService.followUser(userId, userIdToFollow);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}/followers/count")
    public ResponseEntity<UserFollowsCountResponseDTO> getFollowersCountUser(@PathVariable Long userId) {
        UserFollowsCountResponseDTO userFollowsCountResponseDTO = userService.followersCountUser(userId);

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

    @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> getProductsFollowed(@PathVariable Long userId, @PathVariable Long userIdToUnfollow) {
        userService.unfollowUser(userId, userIdToUnfollow);

        return ResponseEntity.ok().build();
    }
}
