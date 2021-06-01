package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserCreateResponseDTO {
    private Long id;
    private String userName;

    public UserCreateResponseDTO() {
    }

    public UserCreateResponseDTO(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
