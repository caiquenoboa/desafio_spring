package com.mercadolibre.desafio_spring.controllers;

import com.mercadolibre.desafio_spring.models.Usuario;
import com.mercadolibre.desafio_spring.responses.VendedorResponse;
import com.mercadolibre.desafio_spring.services.UsuarioService;
import com.mercadolibre.desafio_spring.services.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<VendedorResponse> getFollowersCount(@PathVariable int userId){
        return ResponseEntity.ok(vendedorService.getFollowersCount(userId));
    }

    // US 0003: Obter uma lista de todos os usuários que seguem um determinado vendedor
    //(quem me segue?)
    // US 0008
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity getFollowersList(@PathVariable int userId, @RequestParam(defaultValue = "name_asc") Optional<String> order){
        return ResponseEntity.ok(vendedorService.getFollowersList(userId, order));
    }

    //US 0004: Obter uma lista de todos os vendedores que um determinado usuário segue
    //(quem estou seguindo?)
    // US0008
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<Usuario> getFollowedList(@PathVariable int userId, @RequestParam(defaultValue = "name_asc") Optional<String> order){
        return ResponseEntity.ok(usuarioService.getFollowedList(userId, order));
    }

    //US 0007: Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um
    //determinado vendedor.
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        usuarioService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }
}
