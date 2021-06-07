package com.mercadolivre.desafioSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromoPostToCreateRequest extends PostToCreateRequest{
    private Boolean hasPromo;
    private Double discount;

    public PromoPostToCreateRequest(PostToCreateRequest postToCreateRequest, Boolean hasPromo, Double discount){
        super(postToCreateRequest.getUserId(), postToCreateRequest.getDate(), postToCreateRequest.getDetail(),
                postToCreateRequest.getCategory(), postToCreateRequest.getPrice());
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
