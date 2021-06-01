package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.dtos.UserDTO;
import com.mercadolivre.desafioSpring.models.Post;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO userDTO) {
        return userRepository.save(this.toModel(userDTO));
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User toModel(UserDTO userDTO) {
        return new User(null, userDTO.getUserName(), userDTO.getSeller(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Boolean followUser(Integer userId, Integer userIdToFollow) {
        User user = this.findById(userId);
        User userToFollow = this.findById(userIdToFollow);
        if(user != null && userToFollow != null
                && !user.getUserId().equals(userToFollow.getUserId())
                && !user.getFollowers().contains(userToFollow)){
            user.getFollowers().add(userToFollow);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
