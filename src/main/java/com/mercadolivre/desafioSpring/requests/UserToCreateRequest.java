package com.mercadolivre.desafioSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserToCreateRequest {
    @NotNull(message = "Voce deve informar o userName")
    private String userName;
    @NotNull(message = "Voce deve informar a variavel isSeller (false para usuario e true para vendedor)")
    private Boolean isSeller;
}
