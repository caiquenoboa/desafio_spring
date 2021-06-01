package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.userDTOs.*;

public interface IUserService {
    UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO);

    UserFollowersResponseDTO followUser(UserFollowersRequestDTO userFollowRequestDTO);

    UserFollowsCountResponseDTO followersCountUser(UserFollowsCountRequestDTO userFollowsCountRequestDTO);

    UserFollowersListResponseDTO followersListUser(UserFollowersListRequestDTO userFollowersListRequestDTO);

    UserFollowedListResponseDTO followedListUser(UserFollowedListRequestDTO userFollowedListRequestDTO);
}
