package com.mercadolibre.desafio_spring.models;

public class UsuarioDTO {
    private int id;
    private String name;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id, String name) {
        this.id = id;
        this.name = name;
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
}
