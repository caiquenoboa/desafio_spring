package com.mercadolivre.desafio_sring.controllers;

import com.mercadolivre.desafio_sring.dtos.postDTOs.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostGetFollowedPostsRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.ProductCreateResponseDTO;
import com.mercadolivre.desafio_sring.services.PostService;
import com.mercadolivre.desafio_sring.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private PostService postService;

    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/newpost")
    public ResponseEntity<PostCreateResponseDTO> createPost(@RequestBody @Valid PostCreateRequestDTO postCreateRequestDTO) {
        PostCreateResponseDTO postCreateResponseDTO = postService.createPost(postCreateRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(postCreateResponseDTO);
    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<PostGetFollowedPostsResponseDTO> getProductsFollowed(@PathVariable Long userId) {
        PostGetFollowedPostsRequestDTO postGetFollowedPostsRequestDTO = new PostGetFollowedPostsRequestDTO(userId);
        PostGetFollowedPostsResponseDTO postGetFollowedPostsResponseDTO = postService.getFollowedPosts(postGetFollowedPostsRequestDTO);

        return ResponseEntity.ok(postGetFollowedPostsResponseDTO);
    }
}
