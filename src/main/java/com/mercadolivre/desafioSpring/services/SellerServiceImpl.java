package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.UserNotFoundException;
import com.mercadolivre.desafioSpring.models.Seller;
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
    private final UserRepository userRepository;

    public UserInfoResponse createSeller(UserToCreateRequest userToCreateRequest) {
        Seller seller = sellerRepository.save(this.toModel(userToCreateRequest));
        return new UserInfoResponse(seller.getId(), seller.getUserName(), true);
    }

    public Seller findById(Integer sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    public Seller toModel(UserToCreateRequest userToCreateRequest) {
        return new Seller(null, userToCreateRequest.getUserName(), null, null,null);
    }

    public SellerFollowersResponse getFollowersNumber(Integer sellerId) {
        Seller seller = this.findById(sellerId);
        if(seller != null ) {
            //return new SellerFollowersResponse(seller.getId(), seller.getUserName(), seller.getFollowers().size());
            return new SellerFollowersResponse(seller.getId(), seller.getUserName(), userRepository.countByFollowedId(sellerId));

        }
        throw new UserNotFoundException("Vendedor " + sellerId + " nao encontrado.");
    }

    public SellerFollowersInfoResponse getFollowersInfo(Integer sellerId) {
        Seller seller = this.findById(sellerId);
        if(seller != null ) {
            List<UserInfoResponse> followersInfoResponseList = seller.getFollowers().stream()
                                                                      .map(follower -> new UserInfoResponse(follower.getId(),
                                                                                                      follower.getUserName(),
                                                                                               false))
                                                                      .collect(Collectors.toList());
            return new SellerFollowersInfoResponse(seller.getId(), seller.getUserName(), followersInfoResponseList);
        }
        throw new UserNotFoundException("Vendedor " + sellerId + " nao encontrado.");
    }
}
