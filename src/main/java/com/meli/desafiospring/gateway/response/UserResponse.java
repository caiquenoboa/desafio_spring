package com.meli.desafiospring.gateway.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String atributoA;
    private String atributoB;

}
