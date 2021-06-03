package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.PostRepository;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.PostInfoResponse;
import com.mercadolivre.desafioSpring.responses.PostsBySellersFollowedResponse;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ProductService productService;
    private final SellerService sellerService;
    private final UserService userService;

    @Override
    public Post toModel(PostToCreateRequest postToCreateRequest, ProductInfoResponse productInfoResponse) {
        Seller seller = sellerService.findById(postToCreateRequest.getUserId());
        if(seller == null) {
            throw new StandardNotFoundException("Vendedor " + postToCreateRequest.getUserId() + " nao encontrado");
        }
        if(postToCreateRequest.getDate() == null){
            postToCreateRequest.setDate(LocalDate.now());
        }
        return new Post(null, seller, postToCreateRequest.getDate(),
                productService.findById(productInfoResponse.getProduct_id()),
                postToCreateRequest.getCategory(), postToCreateRequest.getPrice());
    }

    @Override
    public PostInfoResponse fromModel(Post post) {
        ProductInfoResponse productInfoResponse = productService.fromModel(post.getProduct());
        return new PostInfoResponse(post.getSeller().getId(), post.getId(), post.getDate(),
                productInfoResponse, post.getCategory(), post.getPrice());
    }

    @Override
    public PostInfoResponse createPost(PostToCreateRequest postToCreateRequest) {
        ProductInfoResponse productInfoResponse = productService.createProduct(postToCreateRequest.getDetail());
        Post post = postRepository.save(this.toModel(postToCreateRequest, productInfoResponse));
        return new PostInfoResponse(post.getId(), post.getSeller().getId(), post.getDate(),
                productInfoResponse, post.getCategory(), post.getPrice());
    }

    @Override
    public PostsBySellersFollowedResponse getAllLastPostsBySellersFollowed(Integer userId, String order) {
        User user = userService.findById(userId);
        if(user == null){
            throw new StandardNotFoundException("Usuario " + userId + " nao encontrado");
        }
        List<Post> posts = new ArrayList<>();
        for (Seller seller: user.getFollowed()) {
            posts.addAll(seller.getPosts().stream().filter(post -> (LocalDate.now().compareTo(post.getDate())) < 14)
                         .collect(Collectors.toList()));
        }
        return new PostsBySellersFollowedResponse(userId, posts.stream()
                                                  .map(this::fromModel).collect(Collectors.toList()));
    }
}
