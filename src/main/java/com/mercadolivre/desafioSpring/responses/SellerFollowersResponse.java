package com.mercadolivre.desafioSpring.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerFollowersResponse {
    private Integer sellerId;
    private String sellerName;
    private Integer followersCount;
}
