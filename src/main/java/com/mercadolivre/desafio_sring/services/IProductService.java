package com.mercadolivre.desafio_sring.services;

import com.mercadolivre.desafio_sring.dtos.productDTOs.request.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.models.Product;

public interface IProductService {
    Product createProduct(ProductCreateRequestDTO productCreateRequestDTO);
}
