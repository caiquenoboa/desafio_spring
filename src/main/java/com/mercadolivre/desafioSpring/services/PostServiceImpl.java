package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.Product;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.repositories.PostRepository;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.PostInfoResponse;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ProductService productService;
    private final SellerService sellerService;

    public Post toModel(PostToCreateRequest postToCreateRequest) {
        Product product = productService.findById(postToCreateRequest.getProductToCreateRequest().getProductId());
        Seller seller = sellerService.findById(postToCreateRequest.getSellerId());
        return new Post(null, seller, postToCreateRequest.getDate(),
                product,
                postToCreateRequest.getCategory(), postToCreateRequest.getPrice());
    }

    public PostInfoResponse createPost(PostToCreateRequest postToCreateRequest) {
        ProductInfoResponse productInfoResponse = productService.createProduct(postToCreateRequest.getProductToCreateRequest());
        Post post = postRepository.save(this.toModel(postToCreateRequest));
        return new PostInfoResponse(post.getId(), post.getSeller().getId(), post.getDate(),
                productInfoResponse, post.getCategory(), post.getPrice());
    }
}
