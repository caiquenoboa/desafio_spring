package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.UserFollowedInfoResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;

public interface UserService {
    UserInfoResponse createUser(UserToCreateRequest userToCreateRequest);
    User findById(Integer userId);
    User toModel(UserToCreateRequest userToCreateRequest);
    UserFollowedInfoResponse getFollowedInfo(Integer userId);
    Boolean isUserValidToFollow(User user, Seller sellerToFollow, Integer userId, Integer sellerIdToFollow);
    Integer countByFollowedId(Integer userId);

    void followSeller(Seller sellerToFollow, Integer userId, Integer sellerIdToFollow);
}
