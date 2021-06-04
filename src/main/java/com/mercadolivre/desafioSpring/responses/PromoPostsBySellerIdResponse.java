package com.mercadolivre.desafioSpring.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromoPostsBySellerIdResponse {
    private Integer userId;
    private String userName;
    List<PostInfoResponse> posts;
}
