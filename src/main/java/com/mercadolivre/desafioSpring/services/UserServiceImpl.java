package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.UserNotFoundException;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.UserRepository;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.UserFollowedInfoResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final SellerService sellerService;

    public UserInfoResponse createUser(UserToCreateRequest userToCreateRequest) {
        User user = userRepository.save(this.toModel(userToCreateRequest));
        return new UserInfoResponse(user.getId(), user.getUserName(), false);
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User toModel(UserToCreateRequest userToCreateRequest) {
        return new User(null, userToCreateRequest.getUserName(), null);
    }

    public void followSeller(Integer userId, Integer sellerIdToFollow) {
        User user = this.findById(userId);
        Seller sellerToFollow = sellerService.findById(sellerIdToFollow);

        if(!isUserValidToFollow(user, sellerToFollow)) {
            throw new UserNotFoundException("Falha ao usuario " + userId + " seguir o usuario " +
                    sellerIdToFollow);
        }
        user.getFollowed().add(sellerToFollow);
        userRepository.save(user);
    }

    public UserFollowedInfoResponse getFollowedInfo(Integer userId) {
        User user = this.findById(userId);
        if(user != null ) {
            List<UserInfoResponse> followedInfoResponseList = user.getFollowed().stream()
                    .map(follower -> new UserInfoResponse(follower.getId(),
                            follower.getUserName(),
                            false))
                    .collect(Collectors.toList());
            return new UserFollowedInfoResponse(user.getId(), user.getUserName(), followedInfoResponseList);
        }
        throw new UserNotFoundException("Usuario " + userId + " nao encontrado.");
    }

    public Boolean isUserValidToFollow(User user, Seller sellerToFollow){
        return user != null && sellerToFollow != null
                && !user.getId().equals(sellerToFollow.getId())
                && !user.getFollowed().contains(sellerToFollow);
    }
}
