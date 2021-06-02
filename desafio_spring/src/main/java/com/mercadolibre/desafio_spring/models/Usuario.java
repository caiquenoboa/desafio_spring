package com.mercadolibre.desafio_spring.models;

import com.mercadolibre.desafio_spring.DTOs.VendedorDTO;

import java.util.List;

public class Usuario {
    private int userId;
    private String userName;
    private List<VendedorDTO> followed;

    public Usuario() {
    }

    public Usuario(int userId, String userName, List<VendedorDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
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

    public List<VendedorDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<VendedorDTO> followed) {
        this.followed = followed;
    }
}
