package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.dtos.UserDTO;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.UserRepository;

public interface UserService {
    User createUser(UserDTO userDTO);
    User toModel(UserDTO userDTO);
}
