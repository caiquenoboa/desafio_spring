package com.mercadolivre.desafioSpring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.SellerFollowersInfoResponse;
import com.mercadolivre.desafioSpring.responses.SellerFollowersResponse;
import com.mercadolivre.desafioSpring.responses.UserFollowedInfoResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;
import com.mercadolivre.desafioSpring.services.SellerService;
import com.mercadolivre.desafioSpring.services.UserService;
import com.mercadolivre.desafioSpring.views.UserView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<UserInfoResponse> createUser(@RequestBody @Valid UserToCreateRequest userToCreateRequest) {
        if(userToCreateRequest.getIsSeller()){
            return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.createSeller(userToCreateRequest));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userToCreateRequest));
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> followSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        sellerService.followSeller(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/{userId}/followers/count/")
    public ResponseEntity<SellerFollowersResponse> getFollowersNumber(@PathVariable Integer userId) {
        SellerFollowersResponse sellerFollowersResponse = sellerService.getFollowersNumber(userId);
        return ResponseEntity.status(HttpStatus.OK).body(sellerFollowersResponse);
    }

    @GetMapping(path = "/{userId}/followers/list/")
    @JsonView(UserView.Simple.class)
    public ResponseEntity<SellerFollowersInfoResponse> getFollowers(@PathVariable Integer userId) {
        SellerFollowersInfoResponse sellerFollowersInfoResponse = sellerService.getFollowersInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(sellerFollowersInfoResponse);
    }


    @GetMapping(path = "/{userId}/followed/list")
    @JsonView(UserView.Simple.class)
    public ResponseEntity<UserFollowedInfoResponse> getFollowed(@PathVariable Integer userId) {
        UserFollowedInfoResponse userFollowersInfoResponse = userService.getFollowedInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userFollowersInfoResponse);
    }
}
