package com.meli.desafiospring.gateway.controller;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.service.user.followed.FollowedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserFollowedController {

    private final FollowedService followedService;

    @GetMapping(value = "/{userId}/followed/list")
    public ResponseEntity<UserResponse> getAllFollowerOfUser(@PathVariable Integer userId,
                                             @RequestParam(name = "order", defaultValue = "name_asc") String order){
        UserResponse userResponse = followedService.getAllFollowed(userId, order);

        return ResponseEntity.ok(userResponse);
    }
}
