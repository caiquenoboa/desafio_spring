package com.mercadolivre.desafioSpring.responses;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolivre.desafioSpring.views.PostView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfoResponse {
    @JsonView(PostView.PromotionalDetailed.class)
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
