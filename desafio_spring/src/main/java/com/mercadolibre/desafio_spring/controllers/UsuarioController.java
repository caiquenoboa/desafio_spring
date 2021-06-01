package com.mercadolibre.desafio_spring.controllers;

import com.mercadolibre.desafio_spring.models.Usuario;
import com.mercadolibre.desafio_spring.services.UsuarioService;
import com.mercadolibre.desafio_spring.services.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private UsuarioService usuarioService;
    private VendedorService vendedorService;

    public UsuarioController(UsuarioService usuarioService, VendedorService vendedorService) {
        this.usuarioService = usuarioService;
        this.vendedorService = vendedorService;
    }

    // US 0001: Ser capaz de realizar a ação de "Follow" (seguir) a um determinado vendedor
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable int userId, @PathVariable int userIdToFollow){
        usuarioService.follow(userId, userIdToFollow);

        return ResponseEntity.ok().build();
    }

    // US 0002: Obter o resultado do número de usuários que seguem um determinado
    //vendedor
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity getFollowersCount(@PathVariable int userId){
        return ResponseEntity.ok(vendedorService.getFollowersCount(userId));
    }

    // US 0003: Obter uma lista de todos os usuários que seguem um determinado vendedor
    //(quem me segue?)
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity getFollowersList(@PathVariable int userId){
        return ResponseEntity.ok(vendedorService.getFoloowersList(userId));
    }

    //US 0004: Obter uma lista de todos os vendedores que um determinado usuário segue
    //(quem estou seguindo?)
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<Usuario> getFollowedList(@PathVariable int userId){
        return ResponseEntity.ok(usuarioService.getFollowedList(userId));
    }
}
