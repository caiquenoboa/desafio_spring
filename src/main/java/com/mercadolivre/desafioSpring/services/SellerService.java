package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.SellerFollowersInfoResponse;
import com.mercadolivre.desafioSpring.responses.SellerFollowersResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;

public interface SellerService {
    UserInfoResponse createSeller(UserToCreateRequest userToCreateRequest);
    Seller toModel(UserToCreateRequest userToCreateRequest);
    Seller findById(Integer sellerIdToFollow);
    SellerFollowersResponse getFollowersNumber(Integer sellerId);
    SellerFollowersInfoResponse getFollowersInfo(Integer sellerId);
}
