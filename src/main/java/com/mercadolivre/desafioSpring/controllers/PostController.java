package com.mercadolivre.desafioSpring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.PostInfoResponse;
import com.mercadolivre.desafioSpring.responses.PostsBySellersFollowedResponse;
import com.mercadolivre.desafioSpring.services.PostService;
import com.mercadolivre.desafioSpring.views.UserView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class PostController {

    private final PostService postService;

    @PostMapping("/newpost")
    public ResponseEntity<PostInfoResponse> createPost(@RequestBody @Valid PostToCreateRequest postToCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postToCreateRequest));
    }

    @GetMapping("/followed/{userId}/list")
    @JsonView(UserView.Simple.class)
    public ResponseEntity<PostsBySellersFollowedResponse> getPostsBySellerFollowed(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.getAllLastPostsBySellersFollowed(userId,"desc"));
    }
}
