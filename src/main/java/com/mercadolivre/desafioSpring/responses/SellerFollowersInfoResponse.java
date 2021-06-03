package com.mercadolivre.desafioSpring.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerFollowersInfoResponse {
    private Integer userId;
    private String userName;
    private List<UserInfoResponse> followers;
}