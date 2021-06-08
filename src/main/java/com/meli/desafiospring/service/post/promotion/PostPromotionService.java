package com.meli.desafiospring.service.post.promotion;

import com.meli.desafiospring.gateway.request.PostPromotionRequest;
import com.meli.desafiospring.service.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostPromotionService {

    private final PostService postService;

    public void create(PostPromotionRequest postRequest) {
        Boolean hasPromo = postRequest.getHasPromo();
        Double discount = postRequest.getDiscount();

        if(hasPromo == null){
            throw new IllegalArgumentException("The field hasPromo is required");
        }

        if(discount == null){
            throw new IllegalArgumentException("The field discount is required");
        }

        if(hasPromo.equals(Boolean.FALSE) && discount > 0){
            postRequest.setDiscount(0.0);
        }

        postService.createPost(postRequest);
    }
}
