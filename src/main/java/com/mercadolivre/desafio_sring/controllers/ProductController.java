package com.mercadolivre.desafio_sring.controllers;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.request.PostPromotionalCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalCountResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalUserListResponseDTO;
import com.mercadolivre.desafio_sring.services.PostPromotionalService;
import com.mercadolivre.desafio_sring.services.PostService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
@Api(tags = "Produtos", description = "Gerenciamento de produtos")
public class ProductController {

    private PostService postService;
    private PostPromotionalService postPromotionalService;

    public ProductController(PostService postService, PostPromotionalService postPromotionalService) {
        this.postService = postService;
        this.postPromotionalService = postPromotionalService;
    }

    @PostMapping(value = "/newpost")
    public ResponseEntity<PostCreateResponseDTO> createPost(@RequestBody @Valid PostCreateRequestDTO postCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postCreateRequestDTO));
    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<PostGetFollowedPostsResponseDTO> getProductsFollowed(@PathVariable Long userId, @RequestParam("order") Optional<String> sort) {
        return ResponseEntity.ok(postService.getFollowedPosts(userId, sort));
    }

    @PostMapping(value = "/newpromopost")
    public ResponseEntity<PostPromotionalCreateResponseDTO> createPostPromotional(@RequestBody @Valid PostPromotionalCreateRequestDTO postPromotionalCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postPromotionalService.createPostPromotional(postPromotionalCreateRequestDTO));
    }

    @GetMapping(value = "{userId}/countPromo")
    public ResponseEntity<PostPromotionalCountResponseDTO> countUserPostPromotional(@PathVariable Long userId) {
        return ResponseEntity.ok(postPromotionalService.countUserPostPromotional(userId));
    }

    @GetMapping(value = "{userId}/list")
    public ResponseEntity<PostPromotionalUserListResponseDTO> getUserPostsPromotional(@PathVariable Long userId) {
        return ResponseEntity.ok(postPromotionalService.getUserPostsPromotional(userId));
    }
}
