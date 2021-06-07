package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.userDTOs.request.UserCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserFollowedListResponseDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserFollowersListResponseDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.UserFollowsCountResponseDTO;
import com.mercadolivre.desafio_sring.models.User;

import java.util.HashMap;
import java.util.Map;

public interface IUserService {
    Map<String, String> mapFieldSort = new HashMap<>() {{
        put("name", "userName");
        put("default_field", "userName");
        put("default_order", "asc");
    }};

    Boolean existsById(Long userId);

    User findUserById(Long userId);

    UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO);

    void followUser(Long userId, Long userIdToFollow);

    UserFollowsCountResponseDTO followersCountUser(Long userId);

    UserFollowersListResponseDTO followersListUser(Long userId, String order);

    UserFollowedListResponseDTO followedListUser(Long userId, String order);

    void unfollowUser(Long userId, Long userIdToUnfollow);
}
