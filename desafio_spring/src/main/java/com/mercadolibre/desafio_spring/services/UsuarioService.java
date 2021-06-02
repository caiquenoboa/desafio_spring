package com.mercadolibre.desafio_spring.services;

import com.mercadolibre.desafio_spring.models.Usuario;
import com.mercadolibre.desafio_spring.models.UsuarioDTO;
import com.mercadolibre.desafio_spring.models.Vendedor;
import com.mercadolibre.desafio_spring.models.VendedorDTO;
import com.mercadolibre.desafio_spring.repositories.UsuarioRepository;
import com.mercadolibre.desafio_spring.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        int lengthUsuario = usuario.getFollowed().size();
        int lengthVendedor = vendedor.getFollowers().size();

        List<VendedorDTO> vendedorDTOList = usuario.getFollowed().stream().filter(vendedorDTO1 -> vendedorDTO1.getUserId() != vendedorDTO.getUserId()).collect(Collectors.toList());
        List<UsuarioDTO> usuarioDTOList = vendedor.getFollowers().stream().filter(usuarioDTO1 -> usuarioDTO.getUserId() != usuarioDTO1.getUserId()).collect(Collectors.toList());

        if(lengthUsuario == vendedorDTOList.size()){
            usuario.getFollowed().add(vendedorDTO);
        }
        else{
            throw new RuntimeException("Usuário já seguia vendedor");
        }

        if(lengthVendedor == usuarioDTOList.size()){
            vendedor.getFollowers().add(usuarioDTO);
        }


        usuarioRepository.update(usuario);
        vendedorRepository.update(vendedor);
    }

    public Usuario getFollowedList(int userId, Optional<String> order){

        Usuario usuario = usuarioRepository.findById(userId);
        if (order.isEmpty()){
            return usuario;
        }
        else if(order.toString().contains("name_asc")){
            List<VendedorDTO> vendedorDTOList = usuario.getFollowed();
            Collections.sort(vendedorDTOList);
            usuario.setFollowed(vendedorDTOList);
        }
        else if(order.toString().contains("name_desc")){
            List<VendedorDTO> vendedorDTOList = usuario.getFollowed();
            Collections.sort(vendedorDTOList, Collections.reverseOrder());
            usuario.setFollowed(vendedorDTOList);
        }
        else{
            throw new RuntimeException("order deve ser 'name_asc' ou 'name_desc'");
        }

        return usuario;

    }

    public void unfollow(int userId, int userIdToUnfollow) {
        Usuario usuario = usuarioRepository.findById(userId);
        Vendedor vendedor = vendedorRepository.findById(userIdToUnfollow);

        UsuarioDTO usuarioDTO = new UsuarioDTO((usuario.getUserId()), usuario.getUserName());
        VendedorDTO vendedorDTO = new VendedorDTO((vendedor.getUserId()), vendedor.getUserName());

        usuario.setFollowed(usuario.getFollowed().stream().filter(vendedorDTO1 -> vendedorDTO1.getUserId() != vendedorDTO.getUserId()).collect(Collectors.toList()));

        vendedor.setFollowers(vendedor.getFollowers().stream().filter(usuarioDTO1 -> usuarioDTO.getUserId() != usuarioDTO1.getUserId()).collect(Collectors.toList()));


        usuarioRepository.update(usuario);
        vendedorRepository.update(vendedor);
    }
}
