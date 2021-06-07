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
    Integer countByFollowedId(Integer userId);
    void followSeller(Seller sellerToFollow, Integer userId);
    void unfollowSeller(Seller sellerToUnFollow, Integer userId);
    UserFollowedInfoResponse getFollowedInfo(Integer userId, String order);
    User validateUser(Integer userId);
    User validateUserToFollowOrUnfollowSeller(Integer userId, Seller seller, String message);
}
