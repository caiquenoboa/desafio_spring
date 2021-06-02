package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.PostInfoResponse;

public interface PostService {
    Post toModel(PostToCreateRequest postToCreateRequest);
    PostInfoResponse createPost(PostToCreateRequest postToCreateRequest);
}
