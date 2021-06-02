package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.models.VendedorDTO;
import com.mercadolibre.desafio_spring.repositories.PublicacaoRepository;
import com.mercadolibre.desafio_spring.repositories.UsuarioRepository;
import com.mercadolibre.desafio_spring.repositories.VendedorRepository;
import com.mercadolibre.desafio_spring.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PublicacaoService {
    private PublicacaoRepository publicacaoRepository;
    private UsuarioRepository usuarioRepository;

    public PublicacaoService(PublicacaoRepository publicacaoRepository, UsuarioRepository usuarioRepository) {
        this.publicacaoRepository = publicacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void createPublicacao(Publicacao publicacao) {
        publicacaoRepository.add(publicacao);
    }

    public List<Publicacao> getPublicacoesList(int userId) {

        List<Publicacao> publicacaoList = publicacaoRepository.findAll();

        List<Publicacao> publicacaoListFiltered = new ArrayList<Publicacao>();

        List<VendedorDTO> vendedorDTOList = usuarioRepository.findById(userId).getFollowed();

        for (VendedorDTO vendedorDTO : vendedorDTOList){
            for (Publicacao publicacao : publicacaoList){
                if(publicacao.getUserId() == vendedorDTO.getUserId()){
                    if(DateUtil.isTheDateInTheLast14Days(publicacao.getDate())){
                        publicacaoListFiltered.add(publicacao);
                    }
                }
            }
        }

        return publicacaoListFiltered;
    }
}
