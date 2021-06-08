package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.PostRepository;
import com.mercadolivre.desafioSpring.requests.PostToCreateRequest;
import com.mercadolivre.desafioSpring.requests.PromoPostToCreateRequest;
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
    public PostInfoResponse createPost(PromoPostToCreateRequest promoPostToCreateRequest) {
        ProductInfoResponse productInfoResponse = productService.createProduct(promoPostToCreateRequest.getDetail());
        Post post = postRepository.save(this.toModel(promoPostToCreateRequest, productInfoResponse));
        return this.fromModel(post);
    }

    @Override
    public PostInfoResponse createPromoPost(PromoPostToCreateRequest promoPostToCreateRequest){
        if(promoPostToCreateRequest.getHasPromo().equals(false) || promoPostToCreateRequest.getDiscount().equals(0.0)){
            throw new StandardNotFoundException("Este produto deve ser promocional e deve ter desconto " +
                    "(endpoint incorreto para esta funcionalidade).");
        }
        return createPost(promoPostToCreateRequest);
    }

    @Override
    public Post toModel(PromoPostToCreateRequest promoPostToCreateRequest, ProductInfoResponse productInfoResponse) {
        Seller seller = sellerService.validateSeller(promoPostToCreateRequest.getUserId());
        if(promoPostToCreateRequest.getDate() == null){
            promoPostToCreateRequest.setDate(LocalDate.now());
        }
        return new Post(null, seller, promoPostToCreateRequest.getDate(),
                productService.findById(productInfoResponse.getProduct_id()),
                promoPostToCreateRequest.getCategory(), promoPostToCreateRequest.getPrice(),
                promoPostToCreateRequest.getHasPromo(), promoPostToCreateRequest.getDiscount());
    }

    @Override
    public PostInfoResponse fromModel(Post post) {
        ProductInfoResponse productInfoResponse = productService.fromModel(post.getProduct());
        return new PostInfoResponse(post.getSeller().getId(), post.getId(), post.getDate(),
                productInfoResponse, post.getCategory(), post.getPrice(), post.getHasPromo(), post.getDiscount());
    }

    @Override
    public PostsBySellersFollowedResponse getAllLastPostsBySellersFollowed(Integer userId, String order) {
        User user = userService.validateUser(userId);
        List<PostInfoResponse> postsInfoResponse = new ArrayList<>();
        for (Seller seller: user.getFollowed()) {
            postsInfoResponse.addAll(seller.getPosts().stream()
                    .map(this::fromModel)
                    .filter(post -> LocalDate.now().minusDays(14).isBefore(post.getDate()))
                    .collect(Collectors.toList()));
        }
        postsInfoResponse.sort(PostInfoResponse.PostInfoResponseNameComparator);
        if(order.toLowerCase().strip().equals("date_desc")){
            Collections.reverse(postsInfoResponse);
        }
        return new PostsBySellersFollowedResponse(userId, postsInfoResponse);
    }

    @Override
    public PromotionalProductsResponse getPromotionalProductsNumber(Integer sellerId) {
        Seller seller = sellerService.validateSeller(sellerId);
        List<Post> promoPosts = seller.getPosts().stream()
                                      .filter(post -> post.getHasPromo().equals(true))
                                      .collect(Collectors.toList());
        return new PromotionalProductsResponse(seller.getId(), seller.getUserName(), promoPosts.size());
    }

    @Override
    public PromoPostsBySellerIdResponse getPromoPostsBySellerId(Integer sellerId) {
        Seller seller = sellerService.validateSeller(sellerId);
        List<PostInfoResponse> postsInfoResponse = seller.getPosts().stream()
                .map(this::fromModel).filter(post -> post.getHasPromo().equals(true))
                .sorted(PostInfoResponse.PostInfoResponseNameComparator)
                .collect(Collectors.toList());
        Collections.reverse(postsInfoResponse);
        return new PromoPostsBySellerIdResponse(seller.getId(), seller.getUserName(), postsInfoResponse);
    }
}
