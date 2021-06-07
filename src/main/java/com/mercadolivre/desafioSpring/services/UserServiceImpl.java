package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.exceptions.StandardNotFoundException;
import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.repositories.UserRepository;
import com.mercadolivre.desafioSpring.requests.UserToCreateRequest;
import com.mercadolivre.desafioSpring.responses.UserFollowedInfoResponse;
import com.mercadolivre.desafioSpring.responses.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserInfoResponse createUser(UserToCreateRequest userToCreateRequest) {
        String userName = userToCreateRequest.getUserName();
        if(userRepository.findByUserName(userName) != null){
            throw new StandardNotFoundException("Nome de usuario invalido (usuario com o nome "
                                                + userName +" ja esta cadastrado no sistema).");
        }
        User user = userRepository.save(this.toModel(userToCreateRequest));
        return new UserInfoResponse(user.getId(), user.getUserName(), false);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User toModel(UserToCreateRequest userToCreateRequest) {
        return new User(null, userToCreateRequest.getUserName(), null);
    }

    @Override
    public Integer countByFollowedId(Integer userId) {
        return userRepository.countByFollowedId(userId);
    }

    @Override
    public void followSeller(Seller sellerToFollow, Integer userId) {
        User user = validateUserToFollowOrUnfollowSeller(userId, sellerToFollow, "follow");
        user.getFollowed().add(sellerToFollow);
        userRepository.save(user);
    }

    @Override
    public void unfollowSeller(Seller sellerToUnFollow, Integer userId) {
        User user = validateUserToFollowOrUnfollowSeller(userId, sellerToUnFollow, "unfollow");
        user.getFollowed().remove(sellerToUnFollow);
        userRepository.save(user);
    }

    @Override
    public UserFollowedInfoResponse getFollowedInfo(Integer userId, String order) {
        User user = validateUser(userId);
        List<UserInfoResponse> followedInfoResponseList = user.getFollowed().stream()
                .map(follower -> new UserInfoResponse(follower.getId(), follower.getUserName(), false))
                .sorted(UserInfoResponse.UserInfoResponseNameComparator)
                .collect(Collectors.toList());
        if(order.toLowerCase().strip().equals("name_desc")){
            Collections.reverse(followedInfoResponseList);
        }
        return new UserFollowedInfoResponse(user.getId(), user.getUserName(), followedInfoResponseList);
    }

    @Override
    public User validateUser(Integer userId) {
        User user = this.findById(userId);
        if(user == null ) {
            throw new StandardNotFoundException("Usuario " + userId + " nao encontrado.");
        }
        return user;
    }

    @Override
    public User validateUserToFollowOrUnfollowSeller(Integer userId, Seller seller, String message) {
        User user = validateUser(userId);
        if(user.getId().equals(seller.getId())){
            throw new StandardNotFoundException("Voce informou dois usuarios iguais com id " + userId);
        } else if(message.equals("follow") && user.getFollowed().contains(seller)){
            throw new StandardNotFoundException("Usuario " + userId + " ja segue o vendedor " + seller.getId());
        } else if(message.equals("unfollow") && !user.getFollowed().contains(seller)){
            throw new StandardNotFoundException("Usuario " + userId + " nao segue o vendedor " + seller.getId());
        }
        return user;
    }
}
