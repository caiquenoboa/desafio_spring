package com.mercadolibre.desafio_spring.controllers;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.services.PublicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity cadastrarPublicacao(@RequestBody @Valid Publicacao publicacao) {
        publicacaoService.createPublicacao(publicacao);
        return ResponseEntity.ok().build();
    }

    //US0006 - Obter uma lista das publicações feitas pelos vendedores que um usuário segue
    //nas últimas duas semanas (para isso, ter em conta ordenação por data, a maioria das
    //publicações recentes primeiro).
    //US0009
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<List<Publicacao>> getListPublicacoes(@PathVariable int userId, @RequestParam(defaultValue = "date_desc") Optional<String> order){

        return ResponseEntity.ok(publicacaoService.getPublicacoesList(userId, order));
    }

    //US00010 - Cadastrar uma nova publicação promocional
    @PostMapping("/newpromopost")
    public ResponseEntity cadastrarPublicacaoPromocional(@RequestBody Publicacao publicacao) {
        publicacaoService.createPublicacao(publicacao);
        return ResponseEntity.ok().build();
    }

    //US00011 - Obtenha o quantidade de produtos promocionais de um vendedor específico
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<Integer> getNumeroProdutosPromocionais(@PathVariable int userId){
        return ResponseEntity.ok(publicacaoService.getNumeroProdutosPromocionais(userId));
    }

    //US00012 - Obter uma lista de todos os produtos promocionais de um vendedor específico
    @GetMapping("/{userId}/list")
    public ResponseEntity<List<Publicacao>> getListaPublicacaoPromo(@PathVariable int userId){
        return ResponseEntity.ok(publicacaoService.getListaPublicacaoPromo(userId));
    }




}