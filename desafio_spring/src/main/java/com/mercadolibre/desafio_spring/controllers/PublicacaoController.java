package com.mercadolibre.desafio_spring.controllers;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.services.PublicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class PublicacaoController {

    private PublicacaoService publicacaoService;

    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    //US0005 - Cadastrar uma nova publicação
    @PostMapping("/newpost")
    public ResponseEntity cadastrarPublicacao(@RequestBody Publicacao publicacao) {
        publicacaoService.createPublicacao(publicacao);
        return ResponseEntity.ok().build();
    }

    //US0006 - Obter uma lista das publicações feitas pelos vendedores que um usuário segue
    //nas últimas duas semanas (para isso, ter em conta ordenação por data, a maioria das
    //publicações recentes primeiro).
    //US0009
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity getListPublicacoes(@PathVariable int userId, @RequestParam("order") Optional<String> order){

        return ResponseEntity.ok(publicacaoService.getPublicacoesList(userId, order));
    }
}