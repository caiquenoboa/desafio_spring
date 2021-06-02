package com.meli.desafiospring.gateway.controller;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.service.user.followed.FollowedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserFollowedController {

    private final FollowedService followedService;

    @GetMapping(value = "/{userId}/followed/list")
    public ResponseEntity<UserResponse> getAllFollowerOfUser(@PathVariable Integer userId){
        UserResponse userResponse = followedService.getAllFollowed(userId);

        return ResponseEntity.ok(userResponse);
    }
}
