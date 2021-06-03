package com.mercadolivre.desafio_sring.controllers;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.services.PostService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
@Api(tags = "Produtos", description = "Gerenciamento de produtos")
public class ProductController {

    private PostService postService;

    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/newpost")
    public ResponseEntity<PostCreateResponseDTO> createPost(@RequestBody @Valid PostCreateRequestDTO postCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postCreateRequestDTO));
    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<PostGetFollowedPostsResponseDTO> getProductsFollowed(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getFollowedPosts(userId));
    }
}
