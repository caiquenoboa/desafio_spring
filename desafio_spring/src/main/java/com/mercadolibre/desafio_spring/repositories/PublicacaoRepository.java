package com.mercadolibre.desafio_spring.repositories;

import com.mercadolibre.desafio_spring.exceptions.PublicacaoNotFoundException;
import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.utils.Json;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PublicacaoRepository {
    public List<Publicacao> findAll() {
        return Json.read(Publicacao.class, "publicacoes.json");
    }

    private void setAll(List<Publicacao> publicacaoList){
        Json.write(publicacaoList, "publicacoes.json");
    }

    public Publicacao findById(int publicacaoId){
        return findAll().stream()
                .filter(publicacao -> publicacao.getId_post() == publicacaoId)
                .findFirst()
                .orElseThrow(() -> new PublicacaoNotFoundException("Publicacao não encontrada"));
    }

    public void update(Publicacao publicacao){
        setAll(findAll().stream()
                .map(publicacao1 -> publicacao.getId_post() == publicacao1.getUserId() ? publicacao : publicacao1)
                .collect(Collectors.toList()));
    }

    public void add(Publicacao publicacao){
        List<Publicacao> publicacaoList = findAll();
        publicacaoList.add(publicacao);
        setAll(publicacaoList);
    }
}
