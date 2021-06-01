package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.dtos.ResponseDTO;
import com.mercadolivre.desafioSpring.dtos.UserDTO;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.responses.UserResponse;

public interface UserService {
    User createUser(UserDTO userDTO);
    User toModel(UserDTO userDTO);

    void followUser(Integer userId, Integer userIdToFollow);

    UserResponse getFollowersNumber(Integer userId);

    ResponseDTO getFollowers(Integer userId);
}
