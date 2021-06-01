package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Usuario;
import com.mercadolibre.desafio_spring.models.UsuarioDTO;
import com.mercadolibre.desafio_spring.models.Vendedor;
import com.mercadolibre.desafio_spring.models.VendedorDTO;
import com.mercadolibre.desafio_spring.repositories.UsuarioRepository;
import com.mercadolibre.desafio_spring.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private VendedorRepository vendedorRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, VendedorRepository vendedorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public void follow(int userId, int userIdToFollow) {
        Usuario usuario = usuarioRepository.findById(userId);
        Vendedor vendedor = vendedorRepository.findById(userIdToFollow);

        UsuarioDTO usuarioDTO = new UsuarioDTO((usuario.getId()), usuario.getName());
        VendedorDTO vendedorDTO = new VendedorDTO((vendedor.getId()), vendedor.getName());

        if(!usuario.getVendedorDTOList().contains(vendedorDTO)){
            usuario.getVendedorDTOList().add(vendedorDTO);
        }

        if(!vendedor.getUsuarioDTOList().contains(usuarioDTO)){
            vendedor.getUsuarioDTOList().add(usuarioDTO);
        }

        usuarioRepository.update(usuario);
        vendedorRepository.update(vendedor);
    }
}
