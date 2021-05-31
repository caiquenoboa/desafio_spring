package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.dtos.UserDTO;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.UserRepository;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        return userRepository.save(toModel(userDTO));
    }

    public User toModel(UserDTO userDTO) {
        return new User(null, userDTO.getUserName(), userDTO.getSeller());
    }

}
