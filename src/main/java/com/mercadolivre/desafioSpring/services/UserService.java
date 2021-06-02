package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.UserFollowedInfoResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;

public interface UserService {
    UserInfoResponse createUser(UserToCreateRequest userToCreateRequest);
    User toModel(UserToCreateRequest userToCreateRequest);
    void followSeller(Integer userId, Integer sellerIdToFollow);
    UserFollowedInfoResponse getFollowedInfo(Integer userId);
}
