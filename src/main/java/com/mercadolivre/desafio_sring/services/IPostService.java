package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostGetFollowedPostsResponseDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface IPostService {
    Map<String, String> mapFieldSort = new HashMap<>() {{
        put("date", "date");
        put("default", "date");
    }};

    PostCreateResponseDTO createPost(PostCreateRequestDTO postCreateRequestDTO);

    PostGetFollowedPostsResponseDTO getFollowedPosts(Long userId, Optional<String> order);
}
