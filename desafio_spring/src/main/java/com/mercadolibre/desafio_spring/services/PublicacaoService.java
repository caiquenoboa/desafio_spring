package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.repositories.PublicacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicacaoService {
    private PublicacaoRepository publicacaoRepository;

    public PublicacaoService(PublicacaoRepository publicacaoRepository) {
        this.publicacaoRepository = publicacaoRepository;
    }

    public void createPublicacao(Publicacao publicacao) {
        publicacaoRepository.add(publicacao);
    }
}
