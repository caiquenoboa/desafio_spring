package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.userDTOs.*;

public interface IUserService {
    UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO);

    void followUser(Long userId, Long userIdToFollow);

    UserFollowsCountResponseDTO followersCountUser(Long userId);

    UserFollowersListResponseDTO followersListUser(UserFollowersListRequestDTO userFollowersListRequestDTO);

    UserFollowedListResponseDTO followedListUser(UserFollowedListRequestDTO userFollowedListRequestDTO);

    void unfollowUser(Long userId, Long userIdToUnfollow);
}
