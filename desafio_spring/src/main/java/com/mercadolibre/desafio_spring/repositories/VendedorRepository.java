package com.mercadolibre.desafio_spring.repositories;

import com.mercadolibre.desafio_spring.exceptions.UsuarioNotFoundException;
import com.mercadolibre.desafio_spring.exceptions.VendedorNotFoundException;
import com.mercadolibre.desafio_spring.models.Usuario;
import com.mercadolibre.desafio_spring.models.Vendedor;
import com.mercadolibre.desafio_spring.utils.Json;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VendedorRepository {
    private List<Vendedor> findAll(){
        return Json.read(Vendedor.class, "vendedores.json");
    }

    private void setAll(List<Vendedor> vendedorList){
        Json.write(vendedorList, "vendedores.json");
    }

    public Vendedor findById(int vendedorId){
        List<Vendedor> vendedorList = findAll();
        return vendedorList.stream()
                .filter(vendedor -> vendedor.getId() == vendedorId)
                .findFirst()
                .orElseThrow(() -> new VendedorNotFoundException("Vendedor não encontrado"));
    }

    public void update(Vendedor vendedor){
        List<Vendedor> vendedorList = findAll();

        setAll((vendedorList.stream()
                .map(vendedorR -> vendedor.getId() == vendedorR.getId() ? vendedor : vendedorR)
                .collect(Collectors.toList())));
    }
}