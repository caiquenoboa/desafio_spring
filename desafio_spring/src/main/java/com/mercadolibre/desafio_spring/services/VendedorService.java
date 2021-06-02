package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.exceptions.UsuarioNotFoundException;
import com.mercadolibre.desafio_spring.models.UsuarioDTO;
import com.mercadolibre.desafio_spring.models.Vendedor;
import com.mercadolibre.desafio_spring.repositories.VendedorRepository;
import com.mercadolibre.desafio_spring.responses.VendedorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {

    private VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public VendedorResponse getFollowersCount(int userId) {
        Vendedor vendedor = vendedorRepository.findById(userId);
        return new VendedorResponse(vendedor.getUserId(), vendedor.getUserName(), vendedor.getFollowers().size());
    }

    public Vendedor getFollowersList(int userId, Optional<String> asc) {
        Vendedor vendedor = vendedorRepository.findById(userId);
        if (asc.isEmpty()){
            return vendedor;
        }
        else if(asc.toString().contains("name_asc")){
            List<UsuarioDTO> usuarioDTOList = vendedor.getFollowers();
            Collections.sort(usuarioDTOList);
            vendedor.setFollowers(usuarioDTOList);
        }
        else if(asc.toString().contains("name_desc")){
            List<UsuarioDTO> usuarioDTOList = vendedor.getFollowers();
            Collections.sort(usuarioDTOList, Collections.reverseOrder());
            vendedor.setFollowers(usuarioDTOList);
        }
        else{
            throw new RuntimeException("order deve ser 'name_asc' ou 'name_desc'");
        }

        return vendedor;
    }
}
