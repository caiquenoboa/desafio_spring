package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.PostRepository;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.responses.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
                postToCreateRequest.getCategory(), postToCreateRequest.getPrice(),
                postToCreateRequest.getHasPromo(), postToCreateRequest.getDiscount());
    }

    @Override
    public PostInfoResponse fromModel(Post post) {
        ProductInfoResponse productInfoResponse = productService.fromModel(post.getProduct());
        return new PostInfoResponse(post.getSeller().getId(), post.getId(), post.getDate(),
                productInfoResponse, post.getCategory(), post.getPrice(), post.getHasPromo(), post.getDiscount());
    }

    @Override
    public PostInfoResponse createPost(PostToCreateRequest postToCreateRequest) {
        ProductInfoResponse productInfoResponse = productService.createProduct(postToCreateRequest.getDetail());
        Post post = postRepository.save(this.toModel(postToCreateRequest, productInfoResponse));
        return this.fromModel(post);
    }

    @Override
    public PostsBySellersFollowedResponse getAllLastPostsBySellersFollowed(Integer userId, String order) {
        User user = userService.findById(userId);
        if(user == null){
            throw new StandardNotFoundException("Usuario " + userId + " nao encontrado");
        }
        List<PostInfoResponse> postsInfoResponse = new ArrayList<>();
        for (Seller seller: user.getFollowed()) {
            postsInfoResponse.addAll(seller.getPosts().stream()
                    .map(this::fromModel)
                    .filter(post -> LocalDate.now().minusDays(14).isBefore(post.getDate()))
                    .collect(Collectors.toList()));
        }
        postsInfoResponse.sort(PostInfoResponse.PostInfoResponseNameComparator);
        if(order.toLowerCase().strip().equals("name_desc")){
            Collections.reverse(postsInfoResponse);
        }
        return new PostsBySellersFollowedResponse(userId, postsInfoResponse);
    }

    @Override
    public PromotionalProductsResponse getPromotionalProductsNumber(Integer userId) {
        Seller seller = sellerService.findById(userId);
        if(seller != null ) {
            List<Post> promoPosts = seller.getPosts().stream()
                                          .filter(post -> post.getHasPromo().equals(true))
                                          .collect(Collectors.toList());
            return new PromotionalProductsResponse(seller.getId(), seller.getUserName(), promoPosts.size());
        }
        throw new StandardNotFoundException("Vendedor " + userId + " nao encontrado.");
    }

    @Override
    public PromoPostsBySellerIdResponse getPromoPostsBySellerId(Integer sellerId) {
        Seller seller = sellerService.findById(sellerId);
        if(seller == null){
            throw new StandardNotFoundException("Vendedor " + sellerId + " nao encontrado");
        }
        List<PostInfoResponse> postsInfoResponse = seller.getPosts().stream()
                .map(this::fromModel)
                .filter(post -> post.getHasPromo().equals(true)).collect(Collectors.toList());
        return new PromoPostsBySellerIdResponse(seller.getId(), seller.getUserName(), postsInfoResponse);
    }
}
