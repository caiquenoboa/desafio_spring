package com.mercadolivre.desafioSpring.responses;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolivre.desafioSpring.views.UserView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponse {

    private Integer userId;
    private String userName;
    @JsonView(UserView.Detailed.class)
    private Boolean isSeller;

}
