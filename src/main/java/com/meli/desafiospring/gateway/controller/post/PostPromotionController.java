package com.meli.desafiospring.gateway.controller.post;

import com.meli.desafiospring.gateway.request.PostPromotionRequest;
import com.meli.desafiospring.gateway.request.PostRequest;
import com.meli.desafiospring.gateway.response.PostPromoResponse;
import com.meli.desafiospring.service.post.promotion.GetQuantityOfProductsPromotionService;
import com.meli.desafiospring.service.post.promotion.PostPromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/products")
public class PostPromotionController {

    private final PostPromotionService postPromotionService;
    private final GetQuantityOfProductsPromotionService productsPromotionService;

    @PostMapping(value = "/newpromopost")
    public ResponseEntity<HttpStatus> createPostPromotion(@RequestBody @Valid PostPromotionRequest postPromotionRequest){
        postPromotionService.create(postPromotionRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/countPromo/")
    public ResponseEntity<PostPromoResponse> getQuantityProductsPromoByUser(@PathVariable Integer userId){
        PostPromoResponse response = productsPromotionService.getByUserId(userId, true);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{userId}/list")
    public ResponseEntity<PostPromoResponse> getProductsPromoByUser(@PathVariable Integer userId){
        PostPromoResponse response = productsPromotionService.getByUserId(userId, false);

        return ResponseEntity.ok(response);
    }
}
