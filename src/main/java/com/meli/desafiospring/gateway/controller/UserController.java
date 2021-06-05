package com.meli.desafiospring.gateway.controller;

import com.meli.desafiospring.gateway.request.UserRequest;
import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.service.user.CreateUserService;
import com.meli.desafiospring.service.user.UserService;
import com.meli.desafiospring.service.user.followers.FollowersService;
import com.meli.desafiospring.service.user.unfollow.UnfollowUserService;
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
    private final FollowersService followersCountService;
    private final UnfollowUserService unfollowUserService;

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

    @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<HttpStatus> unfollowUser(@PathVariable(value = "userId") Integer userId,
                                     @PathVariable(value = "userIdToUnfollow") Integer userIdToUnfollow){

        unfollowUserService.unfollowUser(userId, userIdToUnfollow);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/followers/count/")
    public ResponseEntity<UserResponse> getFollowersCountOfUser(@PathVariable Integer userId){
        UserResponse userResponse = followersCountService.getFollowers(userId,true);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(value = "/{userId}/followers/list")
    public ResponseEntity<UserResponse> getAllFollowerOfUser(@PathVariable Integer userId){
        UserResponse userResponse = followersCountService.getFollowers(userId, false);

        return ResponseEntity.ok(userResponse);
    }

}
