package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.SellerRepository;
import com.mercadolivre.desafioSpring.repositories.UserRepository;
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
    public UserInfoResponse createSeller(UserToCreateRequest userToCreateRequest) {
        Seller seller = sellerRepository.save(this.toModel(userToCreateRequest));
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
        Seller sellerToFollow = this.findById(sellerIdToFollow);
        userService.followSeller(sellerToFollow, userId, sellerIdToFollow);
    }

    @Override
    public SellerFollowersResponse getFollowersNumber(Integer sellerId) {
        Seller seller = this.findById(sellerId);
        if(seller != null ) {
            return new SellerFollowersResponse(seller.getId(), seller.getUserName(), userService.countByFollowedId(sellerId));

        }
        throw new StandardNotFoundException("Vendedor " + sellerId + " nao encontrado.");
    }

    @Override
    public SellerFollowersInfoResponse getFollowersInfo(Integer sellerId) {
        Seller seller = this.findById(sellerId);
        if(seller != null ) {
            List<UserInfoResponse> followListResp = seller.getFollowers().stream()
                                                           .map(follower -> new UserInfoResponse(follower.getId(),
                                                                follower.getUserName(),false))
                                                           .collect(Collectors.toList());
            return new SellerFollowersInfoResponse(seller.getId(), seller.getUserName(), followListResp);
        }
        throw new StandardNotFoundException("Vendedor " + sellerId + " nao encontrado.");
    }
}
