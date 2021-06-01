package com.mercadolivre.desafio_sring.dtos.userDTOs;

import com.mercadolivre.desafio_sring.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserCreateRequestDTO {
    @NotBlank
    private String userName;

    @NotNull
    private Boolean isSeller;

    public UserCreateRequestDTO() {
    }

    public UserCreateRequestDTO(String userName, Boolean isSeller) {
        this.userName = userName;
        this.isSeller = isSeller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Boolean seller) {
        isSeller = seller;
    }

    public User toModel() {
        return new User(null, this.userName, this.isSeller, null, null);
    }
}
