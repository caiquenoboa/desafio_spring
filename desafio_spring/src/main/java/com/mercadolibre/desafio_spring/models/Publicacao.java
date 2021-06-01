package com.mercadolibre.desafio_spring.models;


public class Publicacao {
    private int userId;
    private int id_post;
    private String date;
    private Produto detail;
    private int category;
    private double price;

    public Publicacao() {
    }

    public Publicacao(int userId, int id_post, String date, Produto detail, int category, double price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
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
}
