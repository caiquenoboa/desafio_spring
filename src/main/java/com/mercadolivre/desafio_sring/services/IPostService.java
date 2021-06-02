package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postDTOs.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostGetFollowedPostsRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.models.Post;

public interface IPostService {
    PostCreateResponseDTO createPost(PostCreateRequestDTO postCreateRequestDTO);

    PostGetFollowedPostsResponseDTO getFollowedPosts(PostGetFollowedPostsRequestDTO postGetFollowedPostsRequestDTO);
}
