package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.DTOs.VendedorDTO;
import com.mercadolibre.desafio_spring.repositories.PublicacaoRepository;
import com.mercadolibre.desafio_spring.repositories.UsuarioRepository;
import com.mercadolibre.desafio_spring.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicacaoService {
    private PublicacaoRepository publicacaoRepository;
    private UsuarioRepository usuarioRepository;

    public PublicacaoService(PublicacaoRepository publicacaoRepository, UsuarioRepository usuarioRepository) {
        this.publicacaoRepository = publicacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void createPublicacao(Publicacao publicacao) {
        Date date = DateUtil.convertStringToDate(publicacao.getDate());
        publicacaoRepository.add(publicacao);
    }

    public List<Publicacao> getPublicacoesList(int userId, Optional<String> order) {

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

        if (order.isEmpty()){
            return publicacaoListFiltered;
        }
        else if(order.toString().contains("date_asc")){
            Collections.sort(publicacaoListFiltered);
        }
        else if(order.toString().contains("date_desc")){
            Collections.sort(publicacaoListFiltered, Collections.reverseOrder());
        }
        else{
            throw new RuntimeException("order deve ser 'name_asc' ou 'name_desc'");
        }

        return publicacaoListFiltered;
    }

    public int getNumeroProdutosPromocionais(int userId) {
        return publicacaoRepository.findAll().stream().filter(publicacao -> publicacao.getUserId() == userId && publicacao.isHasPromo()).collect(Collectors.toList()).size();
    }

    public List<Publicacao> getListaPublicacaoPromo(int userId) {
        return publicacaoRepository.findAll().stream().filter(publicacao -> publicacao.getUserId() == userId && publicacao.isHasPromo()).collect(Collectors.toList());
    }
}
