package com.mercadolivre.desafio_sring.models;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PostPromotional extends Post {
    private Boolean hasPromo;
    private Double discount;

    public PostPromotional() {
    }

    public PostPromotional(Long id_post, LocalDate date, Long category, Double price, User user, Product product, Boolean hasPromo, Double discount) {
        super(id_post, date, category, price, user, product);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }
}
