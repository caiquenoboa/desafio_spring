package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.PostInfoResponse;
import com.mercadolivre.desafioSpring.responses.PostsBySellersFollowedResponse;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;
import com.mercadolivre.desafioSpring.responses.PromotionalProductsResponse;

public interface PostService {
    Post toModel(PostToCreateRequest postToCreateRequest, ProductInfoResponse productInfoResponse);
    PostInfoResponse fromModel(Post post);
    PostInfoResponse createPost(PostToCreateRequest postToCreateRequest);
    PostsBySellersFollowedResponse getAllLastPostsBySellersFollowed(Integer userId, String order);
    PromotionalProductsResponse getPromotionalProductsNumber(Integer userId);
}
