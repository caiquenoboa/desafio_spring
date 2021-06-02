package com.mercadolivre.desafioSpring.responses;

import com.mercadolivre.desafioSpring.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerFollowersInfoResponse {
    private Integer sellerId;
    private String sellerName;
    private List<UserInfoResponse> followers;
}