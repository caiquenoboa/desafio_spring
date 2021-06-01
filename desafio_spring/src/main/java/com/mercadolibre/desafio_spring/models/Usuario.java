package com.mercadolibre.desafio_spring.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String name;
    private List<VendedorDTO> vendedorDTOList;

    public Usuario() {
    }

    public Usuario(int id, String name, List<VendedorDTO> vendedorDTOList) {
        this.id = id;
        this.name = name;
        this.vendedorDTOList = vendedorDTOList;
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

    public List<VendedorDTO> getVendedorDTOList() {
        return vendedorDTOList;
    }

    public void setVendedorDTOList(List<VendedorDTO> vendedorDTOList) {
        this.vendedorDTOList = vendedorDTOList;
    }
}
