package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Publicacao;
import com.mercadolibre.desafio_spring.models.VendedorDTO;
import com.mercadolibre.desafio_spring.repositories.PublicacaoRepository;
import com.mercadolibre.desafio_spring.repositories.UsuarioRepository;
import com.mercadolibre.desafio_spring.repositories.VendedorRepository;
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
        String sDate1="31/12/1998";
        try {
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 14);
        Date twoWeeksAgo = c.getTime();



        List<Publicacao> publicacaoList = publicacaoRepository.findAll();

        List<Publicacao> publicacaoListFiltered = new ArrayList<Publicacao>();

        List<VendedorDTO> vendedorDTOList = usuarioRepository.findById(userId).getFollowed();

        for (VendedorDTO vendedorDTO : vendedorDTOList){
            for (Publicacao publicacao : publicacaoList){
                if(publicacao.getUserId() == vendedorDTO.getUserId()){
                    publicacaoListFiltered.add(publicacao);
                }
            }
        }

        return publicacaoListFiltered;
    }
}
