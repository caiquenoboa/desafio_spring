package com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response;

import com.mercadolivre.desafio_sring.dtos.postDTOs.response.PostCreateResponseDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.response.ProductCreateResponseDTO;
import com.mercadolivre.desafio_sring.models.PostPromotional;

import java.time.LocalDate;

public class PostPromotionalCreateResponseDTO extends PostCreateResponseDTO {
    private Boolean hasPromo;
    private Double discount;

    public PostPromotionalCreateResponseDTO() { }

    public PostPromotionalCreateResponseDTO(Long id_post, LocalDate date, ProductCreateResponseDTO detail, Long category, Double price, Boolean hasPromo, Double discount) {
        super(id_post, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public PostPromotionalCreateResponseDTO(PostPromotional postPromotional) {
        super(
                postPromotional.getId_post(),
                postPromotional.getDate(),
                new ProductCreateResponseDTO(postPromotional.getProduct()),
                postPromotional.getCategory(),
                postPromotional.getPrice()
        );
        this.hasPromo = postPromotional.getHasPromo();
        this.discount = postPromotional.getDiscount();
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
