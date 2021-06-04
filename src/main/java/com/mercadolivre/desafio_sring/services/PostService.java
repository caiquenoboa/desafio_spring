package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.exceptions.GeneralException;
import com.mercadolivre.desafio_sring.models.Post;
import com.mercadolivre.desafio_sring.models.Product;
import com.mercadolivre.desafio_sring.models.User;
import com.mercadolivre.desafio_sring.repositories.PostRepository;
import com.mercadolivre.desafio_sring.utils.Sorter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    private final UserService userService;
    private final ProductService productService;

    public PostService(PostRepository postRepository, UserService userService, ProductService productService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public PostCreateResponseDTO createPost(PostCreateRequestDTO postCreateRequestDTO) {
        User user = userService.findUserById(postCreateRequestDTO.getUserId());

        if (!user.getIsSeller()) {
            throw new GeneralException("User is not seller", HttpStatus.BAD_REQUEST.value());
        }

        Product product = productService.createProduct(postCreateRequestDTO.getDetail());
        Post post = postRepository.save(postCreateRequestDTO.toModel(user, product));

        return new PostCreateResponseDTO(post);
    }

    @Override
    public PostGetFollowedPostsResponseDTO getFollowedPosts(Long userId, Optional<String> sort) {
        if (!userService.existsById(userId)) {
            throw new GeneralException("User not found", HttpStatus.NOT_FOUND.value());
        }

        List<Post> postsResult = postRepository
                .findByUserFollowersUserIdAndDateBetween(
                        userId,
                        LocalDate.now().minusDays(14),
                        LocalDate.now(),
                        Sorter.getSort(mapFieldSort, sort)
                );

        List<PostCreateResponseDTO> postsDTO = postsResult
                .stream()
                .map(PostCreateResponseDTO::new)
                .collect(Collectors.toList());

        return new PostGetFollowedPostsResponseDTO(userId, postsDTO);
    }
}
