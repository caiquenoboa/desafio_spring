package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Vendedor;
import com.mercadolibre.desafio_spring.repositories.VendedorRepository;
import com.mercadolibre.desafio_spring.responses.VendedorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class VendedorService {

    private VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public VendedorResponse getFollowersCount(int userId) {
        Vendedor vendedor = vendedorRepository.findById(userId);
        return new VendedorResponse(vendedor.getId(), vendedor.getName(), vendedor.getUsuarioDTOList().size());
    }

    public Vendedor getFoloowersList(int userId) {
        return vendedorRepository.findById(userId);
    }
}
