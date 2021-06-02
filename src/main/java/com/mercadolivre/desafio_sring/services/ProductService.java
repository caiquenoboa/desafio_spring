package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.productDTOs.request.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.models.Product;
import com.mercadolivre.desafio_sring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product createProduct(ProductCreateRequestDTO productCreateRequestDTO) {
        return productRepository.save(productCreateRequestDTO.toModel());
    }
}
