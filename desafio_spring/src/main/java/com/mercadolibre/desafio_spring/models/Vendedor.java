package com.mercadolibre.desafio_spring.models;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private int id;
    private String name;
    private List<UsuarioDTO> usuarioDTOList;

    public Vendedor() {
    }

    public Vendedor(int id, String name, List<UsuarioDTO> usuarioDTOList) {
        this.id = id;
        this.name = name;
        this.usuarioDTOList = usuarioDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UsuarioDTO> getUsuarioDTOList() {
        return usuarioDTOList;
    }

    public void setUsuarioDTOList(List<UsuarioDTO> usuarioDTOList) {
        this.usuarioDTOList = usuarioDTOList;
    }
}
