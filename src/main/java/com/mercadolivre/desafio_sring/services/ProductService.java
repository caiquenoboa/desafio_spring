package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.productDTOs.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.ProductCreateResponseDTO;
import com.mercadolivre.desafio_sring.repositories.PostRepository;
import com.mercadolivre.desafio_sring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
