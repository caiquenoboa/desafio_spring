package com.mercadolivre.desafioSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserToCreateRequest {
    private String userName;
    private Boolean isSeller;
}
