package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.postDTOs.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostGetFollowedPostsRequestDTO;
import com.mercadolivre.desafio_sring.dtos.postDTOs.PostGetFollowedPostsResponseDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.ProductCreateResponseDTO;
import com.mercadolivre.desafio_sring.exceptions.GeneralException;
import com.mercadolivre.desafio_sring.exceptions.ObjectNotFoundException;
import com.mercadolivre.desafio_sring.models.Post;
import com.mercadolivre.desafio_sring.models.Product;
import com.mercadolivre.desafio_sring.models.User;
import com.mercadolivre.desafio_sring.repositories.PostRepository;
import com.mercadolivre.desafio_sring.repositories.ProductRepository;
import com.mercadolivre.desafio_sring.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public PostCreateResponseDTO createPost(PostCreateRequestDTO postCreateRequestDTO) {
        User user = userRepository
                .findById(postCreateRequestDTO.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not a found"));

        if (!user.getIsSeller()) {
            throw new GeneralException("User is not seller", HttpStatus.BAD_REQUEST.value());
        }

        Product product = productRepository.save(postCreateRequestDTO.getDetail().toModel());

        Post post = postRepository.save(postCreateRequestDTO.toModel(user, product));

        ProductCreateResponseDTO productCreateResponseDTO = new ProductCreateResponseDTO(post.getProduct());

        PostCreateResponseDTO postCreateResponseDTO = new PostCreateResponseDTO();
        postCreateResponseDTO.setUserId(post.getUser().getUserId());
        postCreateResponseDTO.setId_post(post.getId_post());
        postCreateResponseDTO.setDate(post.getDate());
        postCreateResponseDTO.setCategory(post.getCategory());
        postCreateResponseDTO.setPrice(post.getPrice());
        postCreateResponseDTO.setDetail(productCreateResponseDTO);

        return postCreateResponseDTO;
    }

    @Override
    public PostGetFollowedPostsResponseDTO getFollowedPosts(PostGetFollowedPostsRequestDTO postGetFollowedPostsRequestDTO) {
        if (!userRepository.existsById(postGetFollowedPostsRequestDTO.getUserId())) {
            throw new GeneralException("User not found", HttpStatus.NOT_FOUND.value());
        }

        List<Post> postsResult = postRepository
                .findByUserFollowersUserIdAndDateBetween(
                        postGetFollowedPostsRequestDTO.getUserId(),
                        LocalDate.now().minusDays(14),
                        LocalDate.now()
                );

        List<PostCreateResponseDTO> postsDTO = new ArrayList<>();

        postsResult.forEach(post -> {
            PostCreateResponseDTO postDTO = new PostCreateResponseDTO();
            postDTO.setId_post(post.getId_post());
            postDTO.setDate(post.getDate());
            postDTO.setCategory(post.getCategory());
            postDTO.setPrice(post.getPrice());

            ProductCreateResponseDTO productCreateResponseDTO = new ProductCreateResponseDTO();
            productCreateResponseDTO.setProduct_id(post.getProduct().getProduct_id());
            productCreateResponseDTO.setProductName(post.getProduct().getProductName());
            productCreateResponseDTO.setType(post.getProduct().getType());
            productCreateResponseDTO.setBrand(post.getProduct().getBrand());
            productCreateResponseDTO.setColor(post.getProduct().getColor());
            productCreateResponseDTO.setNotes(post.getProduct().getNotes());

            postDTO.setDetail(productCreateResponseDTO);

            postsDTO.add(postDTO);
        });

        return new PostGetFollowedPostsResponseDTO(postGetFollowedPostsRequestDTO.getUserId(), postsDTO);
    }
}
