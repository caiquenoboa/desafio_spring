package com.mercadolivre.desafioSpring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.*;
import com.mercadolivre.desafioSpring.services.PostService;
import com.mercadolivre.desafioSpring.views.PostView;
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
        if(postToCreateRequest.getHasPromo().equals(true) || !postToCreateRequest.getDiscount().equals(0.0)){
            throw new StandardNotFoundException("Este produto nao pode ser promocional e nao deve ter desconto " +
                                                "(endpoint incorreto para esta funcionalidade).");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postToCreateRequest));
    }

    @GetMapping("/followed/{userId}/list")
    @JsonView(UserView.Simple.class)
    public ResponseEntity<PostsBySellersFollowedResponse> getPostsBySellerFollowed(@PathVariable Integer userId,
                                                                                   String order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.getAllLastPostsBySellersFollowed(userId,order));
    }

    @PostMapping("/newpromopost")
    @JsonView(PostView.PromotionalDetailed.class)
    public ResponseEntity<PostInfoResponse> createPromotionalPost
            (@RequestBody @Valid PostToCreateRequest postToCreateRequest) {
        if(postToCreateRequest.getHasPromo().equals(false) || postToCreateRequest.getDiscount().equals(0.0)){
            throw new StandardNotFoundException("Este produto deve ser promocional e deve ter desconto " +
                                                "(endpoint incorreto para esta funcionalidade).");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postToCreateRequest));
    }

    @GetMapping(path = "/{userId}/countPromo/")
    public ResponseEntity<PromotionalProductsResponse> getPromotionalProductsNumber(@PathVariable Integer userId) {
        PromotionalProductsResponse promotionalProductsResponse = postService.getPromotionalProductsNumber(userId);
        return ResponseEntity.status(HttpStatus.OK).body(promotionalProductsResponse);
    }

    @GetMapping("/{userId}/list/")
    @JsonView({PostView.PromotionalSimple.class})
    public ResponseEntity<PromoPostsBySellerIdResponse> getPromoPostsBySellerId(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.getPromoPostsBySellerId(userId));
    }
}
