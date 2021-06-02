package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.userDTOs.*;
import com.mercadolivre.desafio_sring.exceptions.GeneralException;
import com.mercadolivre.desafio_sring.exceptions.ObjectNotFoundException;
import com.mercadolivre.desafio_sring.models.User;
import com.mercadolivre.desafio_sring.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = userRepository.save(userCreateRequestDTO.toModel());

        UserCreateResponseDTO userCreateResponseDTO = new UserCreateResponseDTO();
        userCreateResponseDTO.setId(user.getUserId());
        userCreateResponseDTO.setUserName(user.getUserName());

        return userCreateResponseDTO;
    }

    @Override
    public void followUser(Long userId, Long userIdToFollow) {
        if (userId.equals(userIdToFollow)) {
            throw  new GeneralException("The user cannot follow himself", HttpStatus.BAD_REQUEST.value());
        }

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        User userToFollow = userRepository
                .findById(userIdToFollow)
                .orElseThrow(() -> new ObjectNotFoundException("User to follow not found"));

        if (user.getIsSeller()) {
            throw  new GeneralException("User is a seller", HttpStatus.BAD_REQUEST.value());
        }

        if (!userToFollow.getIsSeller()) {
            throw  new GeneralException("UserToFollow is not a seller", HttpStatus.BAD_REQUEST.value());
        }

        if (user.getFollowing().contains(userToFollow)) {
            throw  new GeneralException("This userId is already following userToFollow", HttpStatus.BAD_REQUEST.value());
        }

        user.addUsersFollowing(userToFollow);
        userRepository.save(user);
    }

    @Override
    public UserFollowsCountResponseDTO followersCountUser(Long userId) {
        UserFollowsCountResponseDTO userFollowsCountResponseDTO = new UserFollowsCountResponseDTO();

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (!user.getIsSeller()) {
            throw new GeneralException("User not is a seller", HttpStatus.BAD_REQUEST.value());
        }

        Long count = userRepository.countByFollowingUserId(user.getUserId());

        userFollowsCountResponseDTO.setUserId(user.getUserId());
        userFollowsCountResponseDTO.setUserName(user.getUserName());
        userFollowsCountResponseDTO.setFollowers_count(count);

        return userFollowsCountResponseDTO;
    }

    @Override
    public UserFollowersListResponseDTO followersListUser(UserFollowersListRequestDTO userFollowersListRequestDTO) {
        UserFollowersListResponseDTO userFollowersListResponseDTO = new UserFollowersListResponseDTO();

        User user = userRepository
                .findById(userFollowersListRequestDTO.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (!user.getIsSeller()) {
            throw new GeneralException("User not is a seller", HttpStatus.BAD_REQUEST.value());
        }

        userFollowersListResponseDTO.setUserId(user.getUserId());
        userFollowersListResponseDTO.setUserName(user.getUserName());

        List<UserFollowersListUserResponseDTO> followersDTO = new ArrayList<>();

        for (User follower : user.getFollowers()) {
            UserFollowersListUserResponseDTO followerDTO = new UserFollowersListUserResponseDTO();
            followerDTO.setUserId(follower.getUserId());
            followerDTO.setUserName(follower.getUserName());
            followersDTO.add(followerDTO);
        }

        userFollowersListResponseDTO.setFollowers(followersDTO);

        return userFollowersListResponseDTO;
    }

    @Override
    public UserFollowedListResponseDTO followedListUser(UserFollowedListRequestDTO userFollowedListRequestDTO) {
        UserFollowedListResponseDTO userFollowedListResponseDTO = new UserFollowedListResponseDTO();

        User user = userRepository
                .findById(userFollowedListRequestDTO.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (user.getIsSeller()) {
            throw new GeneralException("User is a seller", HttpStatus.BAD_REQUEST.value());
        }

        userFollowedListResponseDTO.setUserId(user.getUserId());
        userFollowedListResponseDTO.setUserName(user.getUserName());

        List<UserFollowedListUserResponseDTO> followedDTO = new ArrayList<>();

        for (User follower : user.getFollowing()) {
            UserFollowedListUserResponseDTO followDTO = new UserFollowedListUserResponseDTO();
            followDTO.setUserId(follower.getUserId());
            followDTO.setUserName(follower.getUserName());
            followedDTO.add(followDTO);
        }

        userFollowedListResponseDTO.setFollowing(followedDTO);

        return userFollowedListResponseDTO;
    }

    @Override
    public void unfollowUser(Long userId, Long userIdToUnfollow) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        User userFollowing = user.getFollowing()
                .stream()
                .filter(x -> x.getUserId().equals(userIdToUnfollow))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("User following not found"));

        user.getFollowing().remove(userFollowing);

        userRepository.save(user);
    }
}
