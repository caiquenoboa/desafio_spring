package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.SellerRepository;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.SellerFollowersInfoResponse;
import com.mercadolivre.desafioSpring.responses.SellerFollowersResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SellerServiceImpl implements SellerService{

    private final SellerRepository sellerRepository;
    private final UserService userService;

    @Override
    public UserInfoResponse createSeller(UserToCreateRequest sellerToCreateRequest) {
        String userName = sellerToCreateRequest.getUserName();
        if(sellerRepository.findByUserName(userName) != null){
            throw new StandardNotFoundException("Nome de vendedor invalido (vendedor com o nome "
                    + userName +" ja esta cadastrado no sistema).");
        }
        Seller seller = sellerRepository.save(this.toModel(sellerToCreateRequest));
        return new UserInfoResponse(seller.getId(), seller.getUserName(), true);
    }

    @Override
    public Seller findById(Integer sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    @Override
    public Seller toModel(UserToCreateRequest userToCreateRequest) {
        return new Seller(null, userToCreateRequest.getUserName(), null, null,null);
    }

    @Override
    public void followSeller(Integer userId, Integer sellerIdToFollow) {
        Seller sellerToFollow = this.validateSeller(sellerIdToFollow);
        userService.followSeller(sellerToFollow, userId);
    }

    @Override
    public void unfollowSeller(Integer userId, Integer sellerIdToUnfollow) {
        Seller sellerToUnFollow = this.validateSeller(sellerIdToUnfollow);
        userService.unfollowSeller(sellerToUnFollow, userId);
    }

    @Override
    public SellerFollowersResponse getFollowersNumber(Integer sellerId) {
        Seller seller = this.validateSeller(sellerId);
        return new SellerFollowersResponse(seller.getId(), seller.getUserName(),
                                           userService.countByFollowedId(sellerId));
    }

    @Override
    public SellerFollowersInfoResponse getFollowersInfo(Integer sellerId) {
        Seller seller = this.validateSeller(sellerId);
        List<UserInfoResponse> followListResp = seller.getFollowers().stream()
                                                       .map(follower -> new UserInfoResponse(follower.getId(),
                                                            follower.getUserName(),false))
                                                       .collect(Collectors.toList());
        return new SellerFollowersInfoResponse(seller.getId(), seller.getUserName(), followListResp);
    }

    @Override
    public Seller validateSeller(Integer sellerId) {
        Seller seller = this.findById(sellerId);
        if(seller == null ) {
            throw new StandardNotFoundException("Vendedor " + sellerId + " nao encontrado.");
        }
        return seller;
    }
}
