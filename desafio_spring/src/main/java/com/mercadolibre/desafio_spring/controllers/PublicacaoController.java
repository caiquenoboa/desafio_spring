package com.mercadolibre.desafio_spring.controllers;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.services.PublicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class PublicacaoController {

    private PublicacaoService publicacaoService;

    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    @PostMapping("/newpost")
    public ResponseEntity cadastrarPublicacao(@RequestBody Publicacao publicacao){
        publicacaoService.createPublicacao(publicacao);
        return ResponseEntity.ok().build();
    }
}
