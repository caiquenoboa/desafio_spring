package com.mercadolibre.desafio_spring.models;

import com.mercadolibre.desafio_spring.DTOs.UsuarioDTO;

import java.util.List;

public class Vendedor {
    private int userId;
    private String userName;
    private List<UsuarioDTO> followers;

    public Vendedor() {
    }

    public Vendedor(int userId, String userName, List<UsuarioDTO> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
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

    public List<UsuarioDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UsuarioDTO> followers) {
        this.followers = followers;
    }
}
