package com.meli.desafiospring.gateway.controller;

import com.meli.desafiospring.gateway.request.PostRequest;
import com.meli.desafiospring.service.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/product/")
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/newpost")
    public ResponseEntity<HttpStatus> createPost(@Valid @RequestBody PostRequest postRequest){
        postService.createPost(postRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
