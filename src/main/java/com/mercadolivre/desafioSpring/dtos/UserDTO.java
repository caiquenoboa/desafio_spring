package com.mercadolivre.desafioSpring.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotBlank(message = "Campo obrigatório")
    private String userName;

    @NotNull(message = "Campo obrigatório")
    private Boolean isSeller;

    public UserDTO() {
    }

    public UserDTO(String userName, Boolean isSeller) {
        this.userName = userName;
        this.isSeller = isSeller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSeller() {
        return isSeller;
    }

    public void setSeller(Boolean seller) {
        isSeller = seller;
    }

}
