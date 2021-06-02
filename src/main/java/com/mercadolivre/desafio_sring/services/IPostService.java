package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostGetFollowedPostsResponseDTO;

public interface IPostService {
    PostCreateResponseDTO createPost(PostCreateRequestDTO postCreateRequestDTO);

    PostGetFollowedPostsResponseDTO getFollowedPosts(Long userId);
}
