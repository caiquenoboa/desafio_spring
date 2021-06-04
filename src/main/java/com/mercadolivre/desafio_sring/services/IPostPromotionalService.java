package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.request.PostPromotionalCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalCountResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalUserListResponseDTO;

public interface IPostPromotionalService {
    PostPromotionalCreateResponseDTO createPostPromotional(PostPromotionalCreateRequestDTO postPromotionalCreateRequestDTO);

    PostPromotionalCountResponseDTO countUserPostPromotional(Long userId);

    PostPromotionalUserListResponseDTO getUserPostsPromotional(Long userId);
}
