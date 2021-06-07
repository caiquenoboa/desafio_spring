package com.mercadolivre.desafio_sring.controllers;

import com.mercadolivre.desafio_sring.dtos.userDTOs.request.UserCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserFollowedListResponseDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserFollowersListResponseDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserFollowsCountResponseDTO;
import com.mercadolivre.desafio_sring.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "Usuários", description = "Gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateRequestDTO));
    }

    @PostMapping(value = "{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> createFollowUser(@PathVariable Long userId, @PathVariable Long userIdToFollow) {
        userService.followUser(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}/followers/count")
    public ResponseEntity<UserFollowsCountResponseDTO> getFollowersCountUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.followersCountUser(userId));
    }

    @GetMapping(value = "{userId}/followers/list")
    public ResponseEntity<UserFollowersListResponseDTO> getFollowersListUser(@PathVariable Long userId, @RequestParam(value = "order", defaultValue = "") String sort) {
        return ResponseEntity.ok(userService.followersListUser(userId, sort));
    }

    @GetMapping(value = "{userId}/followed/list")
    public ResponseEntity<UserFollowedListResponseDTO> getFollowedListUser(@PathVariable Long userId, @RequestParam(value = "order", defaultValue = "") String sort) {
        return ResponseEntity.ok(userService.followedListUser(userId, sort));
    }

    @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> getProductsFollowed(@PathVariable Long userId, @PathVariable Long userIdToUnfollow) {
        userService.unfollowUser(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }
}
