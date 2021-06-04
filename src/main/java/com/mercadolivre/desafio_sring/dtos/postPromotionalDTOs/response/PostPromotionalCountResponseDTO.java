package com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response;

public class PostPromotionalCountResponseDTO {
    private Long userId;
    private String userName;
    private Long promoproducts_count;

    public PostPromotionalCountResponseDTO() {

    }

    public PostPromotionalCountResponseDTO(Long userId, String userName, Long promoproducts_count) {
        this.userId = userId;
        this.userName = userName;
        this.promoproducts_count = promoproducts_count;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(Long promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
