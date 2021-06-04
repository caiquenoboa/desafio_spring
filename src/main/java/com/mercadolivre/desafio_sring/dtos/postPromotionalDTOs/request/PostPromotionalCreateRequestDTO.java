package com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.request;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.request.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.models.PostPromotional;
import com.mercadolivre.desafio_sring.models.Product;
import com.mercadolivre.desafio_sring.models.User;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PostPromotionalCreateRequestDTO extends PostCreateRequestDTO {
    @NotNull
    @AssertTrue
    private Boolean hasPromo;

    @NotNull
    @Range(min = 0, max = 1)
    private Double discount;

    public PostPromotionalCreateRequestDTO() {
    }

    public PostPromotionalCreateRequestDTO(Long userId, Long category, Double price, LocalDate date, ProductCreateRequestDTO detail, Boolean hasPromo, Double discount) {
        super(userId, category, price, date, detail);
        this.hasPromo = hasPromo;
        this.discount = discount;
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

    @Override
    public PostPromotional toModel(User user, Product product) {
        return new PostPromotional(null, this.date, this.category, this.price, user, product, this.hasPromo, this.discount);
    }
}
