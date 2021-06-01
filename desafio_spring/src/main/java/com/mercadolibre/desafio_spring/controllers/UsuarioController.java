package com.mercadolibre.desafio_spring.controllers;

import com.mercadolibre.desafio_spring.services.UsuarioService;
import com.mercadolibre.desafio_spring.services.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // US 0001: Ser capaz de realizar a ação de "Follow" (seguir) a um determinado vendedor
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable int userId, @PathVariable int userIdToFollow){
        usuarioService.follow(userId, userIdToFollow);

        return ResponseEntity.ok().build();
    }
}
