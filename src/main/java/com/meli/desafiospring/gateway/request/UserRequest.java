package com.meli.desafiospring.gateway.request;

import com.meli.desafiospring.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "User name cannot be null or empty")
    private String name;

    @NotNull(message = "User type cannot be null or empty")
    private UserType type;
}
