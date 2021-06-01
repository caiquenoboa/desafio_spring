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

        UsuarioDTO usuarioDTO = new UsuarioDTO((usuario.getUserId()), usuario.getUserName());
        VendedorDTO vendedorDTO = new VendedorDTO((vendedor.getUserId()), vendedor.getUserName());

        if(!usuario.getFollowed().contains(vendedorDTO)){
            usuario.getFollowed().add(vendedorDTO);
        }

        if(!vendedor.getFollowers().contains(usuarioDTO)){
            vendedor.getFollowers().add(usuarioDTO);
        }

        usuarioRepository.update(usuario);
        vendedorRepository.update(vendedor);
    }

    public Usuario getFollowedList(int userId){
        return usuarioRepository.findById(userId);
    }
}
