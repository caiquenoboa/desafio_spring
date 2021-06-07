package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.requests.PromoPostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface PostService {
    PostInfoResponse createPost(PromoPostToCreateRequest promoPostToCreateRequest);
    PostInfoResponse createPromoPost(PromoPostToCreateRequest promoPostToCreateRequest);
    Post toModel(PromoPostToCreateRequest promoPostToCreateRequest, ProductInfoResponse productInfoResponse);
    PostInfoResponse fromModel(Post post);
    PostsBySellersFollowedResponse getAllLastPostsBySellersFollowed(Integer userId, String order);
    PromotionalProductsResponse getPromotionalProductsNumber(Integer sellerId);
    PromoPostsBySellerIdResponse getPromoPostsBySellerId(Integer sellerId);
}
