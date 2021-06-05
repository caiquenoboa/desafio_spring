package com.meli.desafiospring.gateway.controller.post;

import com.meli.desafiospring.gateway.request.PostRequest;
import com.meli.desafiospring.service.post.promotion.PostPromotionService;
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
@RequestMapping(value = "/products")
public class PostPromotionController {

    private final PostPromotionService postPromotionService;

    @PostMapping(value = "/newpromopost")
    public ResponseEntity<HttpStatus> createPostPromotion(@RequestBody @Valid PostRequest postRequest){
        postPromotionService.create(postRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
