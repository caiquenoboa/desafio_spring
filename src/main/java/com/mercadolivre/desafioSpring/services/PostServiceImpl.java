package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.repositories.PostRepository;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.PostInfoResponse;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ProductService productService;
    private final SellerService sellerService;

    @Override
    public Post toModel(PostToCreateRequest postToCreateRequest) {
        Seller seller = sellerService.findById(postToCreateRequest.getUserId());
        if(seller == null) {
            throw new StandardNotFoundException("Vendedor " + postToCreateRequest.getUserId() + " nao encontrado");
        }
        if(postToCreateRequest.getDate() == null){
            postToCreateRequest.setDate(LocalDate.now());
        }
        return new Post(null, seller, postToCreateRequest.getDate(), null,
                        postToCreateRequest.getCategory(), postToCreateRequest.getPrice());
    }

    @Override
    public PostInfoResponse createPost(PostToCreateRequest postToCreateRequest) {
        ProductInfoResponse productInfoResponse = productService.createProduct(postToCreateRequest.getDetail());
        Post post = postRepository.save(this.toModel(postToCreateRequest));
        return new PostInfoResponse(post.getId(), post.getSeller().getId(), post.getDate(),
                productInfoResponse, post.getCategory(), post.getPrice());
    }
}
