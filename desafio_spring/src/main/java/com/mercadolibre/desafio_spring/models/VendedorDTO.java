package com.mercadolibre.desafio_spring.models;

public class VendedorDTO {
    private int userId;
    private String userName;

    public VendedorDTO() {
    }

    public VendedorDTO(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
