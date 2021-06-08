package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.userDTOs.request.UserCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.userDTOs.response.*;
import com.mercadolivre.desafio_sring.exceptions.GeneralException;
import com.mercadolivre.desafio_sring.exceptions.ObjectNotFoundException;
import com.mercadolivre.desafio_sring.models.User;
import com.mercadolivre.desafio_sring.repositories.UserRepository;
import com.mercadolivre.desafio_sring.utils.Sorter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    public UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {
        return new UserCreateResponseDTO(userRepository.save(userCreateRequestDTO.toModel()));
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
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (!user.getIsSeller()) {
            throw new GeneralException("User not is a seller", HttpStatus.BAD_REQUEST.value());
        }

        Long count = userRepository.countByFollowingUserId(user.getUserId());

        return new UserFollowsCountResponseDTO(user.getUserId(), user.getUserName(), count);
    }

    @Override
    public UserFollowersListResponseDTO followersListUser(Long userId, String sort) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (!user.getIsSeller()) {
            throw new GeneralException("User not is a seller", HttpStatus.BAD_REQUEST.value());
        }

        List<UserFollowersListUserResponseDTO> followersDTO = userRepository
                .findByFollowingUserId(userId, Sorter.getSort(mapFieldSort, sort))
                .stream()
                .map(UserFollowersListUserResponseDTO::new)
                .collect(Collectors.toList());

        return new UserFollowersListResponseDTO(user.getUserId(), user.getUserName(), followersDTO);
    }

    @Override
    public UserFollowedListResponseDTO followedListUser(Long userId, String sort) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        List<UserFollowedListUserResponseDTO> followedDTO = userRepository
                .findByFollowersUserId(userId, Sorter.getSort(mapFieldSort, sort))
                .stream()
                .map(UserFollowedListUserResponseDTO::new)
                .collect(Collectors.toList());

        return new UserFollowedListResponseDTO(user.getUserId(), user.getUserName(), followedDTO);
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
