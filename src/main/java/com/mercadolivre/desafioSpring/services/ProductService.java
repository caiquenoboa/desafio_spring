package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Product;
import com.mercadolivre.desafioSpring.requests.ProductToCreateRequest;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;

public interface ProductService {
    ProductInfoResponse createProduct(ProductToCreateRequest productToCreateRequest);
    Product findById(Integer productId);
    Product toModel(ProductToCreateRequest productToCreateRequest);
    ProductInfoResponse fromModel(Product product);
}
