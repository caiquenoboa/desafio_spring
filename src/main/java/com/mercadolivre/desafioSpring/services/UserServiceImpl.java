package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.dtos.ResponseUserFollowersDTO;
import com.mercadolivre.desafioSpring.dtos.UserDTO;
import com.mercadolivre.desafioSpring.exceptions.UserNotFoundException;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.UserRepository;
import com.mercadolivre.desafioSpring.responses.UserResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

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

    public void followUser(Integer userId, Integer userIdToFollow) {
        User user = this.findById(userId);
        User userToFollow = this.findById(userIdToFollow);

        if(!isUserValidToFollow(user, userToFollow)) {
            throw new UserNotFoundException("Usuario " + userId + " nao pode seguir usuario " + userToFollow.getUserId());
        }
        user.getFollowers().add(userToFollow);
        userRepository.save(user);
    }

    public Boolean isUserValidToFollow(User user, User userToFollow){
        return user != null && userToFollow != null
                && !user.getUserId().equals(userToFollow.getUserId())
                && !user.getFollowers().contains(userToFollow);
    }

    public UserResponse getFollowersNumber(Integer userId) {
        User user = this.findById(userId);
        if(user != null ) {
            return new UserResponse(user.getUserId(), user.getUserName(), user.getFollowers().size());
        }
        throw new UserNotFoundException("Usuario " + userId + " nao encontrado.");
    }

    @Override
    public ResponseUserFollowersDTO getFollowers(Integer userId) {
        User user = this.findById(userId);
        if(user != null ) {
            return new ResponseUserFollowersDTO(200, "", user.getUserId(), user.getUserName(), user.getFollowers());
        }
        return new ResponseUserFollowersDTO(404, "Usuario nao encontrado", null, null, null);
    }
}
