package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Product;
import com.mercadolivre.desafioSpring.models.User;
import com.mercadolivre.desafioSpring.requests.ProductToCreateRequest;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;

public interface ProductService {
    Product toModel(ProductToCreateRequest productToCreateRequest);
    ProductInfoResponse createProduct(ProductToCreateRequest productToCreateRequest);
    Product findById(Integer productId);
}
