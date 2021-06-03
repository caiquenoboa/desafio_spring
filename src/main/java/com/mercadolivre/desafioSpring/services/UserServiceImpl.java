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

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserInfoResponse createUser(UserToCreateRequest userToCreateRequest) {
        User user = userRepository.save(this.toModel(userToCreateRequest));
        return new UserInfoResponse(user.getId(), user.getUserName(), false);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Integer countByFollowedId(Integer userId){return userRepository.countByFollowedId(userId);}

    @Override
    public void followSeller(Seller sellerToFollow, Integer userId, Integer sellerIdToFollow) {
        User user = this.findById(userId);
        if(this.isUserValidToFollow(user, sellerToFollow, userId, sellerIdToFollow)) {
            user.getFollowed().add(sellerToFollow);
            userRepository.save(user);
        }
    }

    @Override
    public void unfollowSeller(Seller sellerToFollow, Integer userId, Integer sellerIdToUnfollow) {
        User user = this.findById(userId);
        if(user.getFollowed().contains(sellerToFollow)) {
            user.getFollowed().remove(sellerToFollow);
            userRepository.save(user);
        }
    }

    @Override
    public User toModel(UserToCreateRequest userToCreateRequest) {
        return new User(null, userToCreateRequest.getUserName(), null);
    }

    @Override
    public UserFollowedInfoResponse getFollowedInfo(Integer userId) {
        User user = this.findById(userId);
        if(user != null ) {
            List<UserInfoResponse> followedInfoResponseList = user.getFollowed().stream()
                    .map(follower -> new UserInfoResponse(follower.getId(), follower.getUserName(),false))
                    .collect(Collectors.toList());
            return new UserFollowedInfoResponse(user.getId(), user.getUserName(), followedInfoResponseList);
        }
        throw new StandardNotFoundException("Usuario " + userId + " nao encontrado.");
    }

    @Override
    public Boolean isUserValidToFollow(User user, Seller sellerToFollow, Integer userId, Integer sellerIdToFollow){
        String message = "";
        if(user == null){
            message += "Usuario " + userId + " nao existe. ";
        }else if(sellerToFollow == null){
            message += "Vendedor " + sellerIdToFollow + " nao existe. ";
        }else if(userId.equals(sellerIdToFollow)){
            message += "Os usuarios nao podem ser iguais. ";
        }else if(user.getFollowed().contains(sellerToFollow)){
            message += "O usuario " + userId + " ja segue o vendedor " + sellerIdToFollow + ". ";
        }
        if(!message.equals("")){ throw new StandardNotFoundException(message); }

        return true;
    }
}
