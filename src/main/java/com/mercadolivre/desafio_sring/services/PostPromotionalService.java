package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.request.PostPromotionalCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalCountResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response.PostPromotionalUserListResponseDTO;
import com.mercadolivre.desafio_sring.exceptions.GeneralException;
import com.mercadolivre.desafio_sring.exceptions.ObjectNotFoundException;
import com.mercadolivre.desafio_sring.models.PostPromotional;
import com.mercadolivre.desafio_sring.models.Product;
import com.mercadolivre.desafio_sring.models.User;
import com.mercadolivre.desafio_sring.repositories.PostPromotionalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostPromotionalService implements IPostPromotionalService {

    private final PostPromotionalRepository postPromotionalRepository;

    private final UserService userService;
    private final ProductService productService;

    public PostPromotionalService(PostPromotionalRepository postPromotionalRepository, UserService userService, ProductService productService) {
        this.postPromotionalRepository = postPromotionalRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public PostPromotionalCreateResponseDTO createPostPromotional(PostPromotionalCreateRequestDTO postPromotionalCreateRequestDTO) {
        User user = userService.findUserById(postPromotionalCreateRequestDTO.getUserId());

        if (!user.getIsSeller()) {
            throw new GeneralException("User is not seller", HttpStatus.BAD_REQUEST.value());
        }

        Product product = productService.createProduct(postPromotionalCreateRequestDTO.getDetail());

        PostPromotional postPromotional = postPromotionalRepository
                .save(postPromotionalCreateRequestDTO.toModel(user, product));

        return new PostPromotionalCreateResponseDTO(postPromotional);
    }

    @Override
    public PostPromotionalCountResponseDTO countUserPostPromotional(Long userId) {
        User user = userService.findUserById(userId);

        if (!user.getIsSeller()) {
            throw new GeneralException("User is not seller", HttpStatus.BAD_REQUEST.value());
        }

        Long count = postPromotionalRepository.countByUserUserId(user.getUserId());
        return new PostPromotionalCountResponseDTO(user.getUserId(), user.getUserName(), count);
    }

    @Override
    public PostPromotionalUserListResponseDTO getUserPostsPromotional(Long userId) {
        User user = userService.findUserById(userId);

        if (!user.getIsSeller()) {
            throw new GeneralException("User is not seller", HttpStatus.BAD_REQUEST.value());
        }

        List<PostPromotional> postsPromotional = postPromotionalRepository.findByUserUserId(userId);

        List<PostPromotionalCreateResponseDTO> postsPromotionalDTO = postsPromotional
                .stream()
                .map(PostPromotionalCreateResponseDTO::new)
                .collect(Collectors.toList());

        return new PostPromotionalUserListResponseDTO(user.getUserId(), user.getUserName(), postsPromotionalDTO);
    }
}
