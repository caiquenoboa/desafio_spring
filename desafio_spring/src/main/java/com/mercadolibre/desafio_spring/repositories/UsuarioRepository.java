package com.mercadolibre.desafio_spring.repositories;

import com.mercadolibre.desafio_spring.exceptions.UsuarioNotFoundException;
import com.mercadolibre.desafio_spring.models.Usuario;
import com.mercadolibre.desafio_spring.models.Vendedor;
import com.mercadolibre.desafio_spring.utils.Json;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepository {
    private List<Usuario> findAll(){
        return Json.read(Usuario.class, "usuarios.json");
    }

    private void setAll(List<Usuario> usuarioList){
        Json.write(usuarioList, "usuarios.json");
    }

    public Usuario findById(int usuarioId){
        List<Usuario> usuarioList = findAll();
        return usuarioList.stream()
                .filter(usuario -> usuario.getUserId() == usuarioId)
                .findFirst()
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado"));
    }

    public void update(Usuario usuario){
        List<Usuario> usuarioList = findAll();

        setAll((usuarioList.stream()
                .map(usuarioR -> usuario.getUserId() == usuarioR.getUserId() ? usuario : usuarioR)
                .collect(Collectors.toList())));
    }
}
