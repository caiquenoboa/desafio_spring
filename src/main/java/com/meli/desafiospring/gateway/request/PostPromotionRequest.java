package com.meli.desafiospring.gateway.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostPromotionRequest extends PostRequest{

    private Boolean hasPromo;
    private Double discount;
}
