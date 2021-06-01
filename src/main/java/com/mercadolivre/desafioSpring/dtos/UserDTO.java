package com.mercadolivre.desafioSpring.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotBlank(message = "Campo obrigatório")
    private String userName;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private Boolean seller;

    public UserDTO() {
    }

    public UserDTO(String userName, Boolean seller) {
        this.userName = userName;
        this.seller = seller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }

}
