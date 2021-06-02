package com.mercadolibre.desafio_spring.models;


import com.mercadolibre.desafio_spring.utils.DateUtil;

import java.util.Date;

public class Publicacao implements Comparable<Publicacao> {
    private int userId;
    private int id_post;
    private String date;
    private Produto detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public Publicacao() {
    }

    public Publicacao(int userId, int id_post, String date, Produto detail, int category, double price, boolean hasPromo, double discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
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
