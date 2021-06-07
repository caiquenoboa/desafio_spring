package com.mercadolibre.desafio_spring.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.desafio_spring.utils.DateUtil;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Publicacao implements Comparable<Publicacao> {
    @NotNull
    private Integer userId;
    @NotNull
    @JsonProperty ("id_post")
    private Integer idPost;
    private String date;
    private Produto detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public Publicacao() {
    }

    public Publicacao(Integer userId, Integer id_post, String date, Produto detail, int category, double price, boolean hasPromo, double discount) {
        this.userId = userId;
        this.idPost = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Produto getDetail() {
        return detail;
    }

    public void setDetail(Produto detail) {
        this.detail = detail;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public int compareTo(Publicacao publicacao) {
        Date thisDate = DateUtil.convertStringToDate(this.getDate());
        Date publicacaoDate = DateUtil.convertStringToDate(publicacao.getDate());
        return thisDate.compareTo(publicacaoDate);
    }
}
