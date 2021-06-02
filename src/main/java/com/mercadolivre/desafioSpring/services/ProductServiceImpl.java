package com.mercadolivre.desafioSpring.services;

import com.mercadolivre.desafioSpring.models.Product;
import com.mercadolivre.desafioSpring.repositories.ProductRepository;
import com.mercadolivre.desafioSpring.requests.ProductToCreateRequest;
import com.mercadolivre.desafioSpring.responses.ProductInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public Product toModel(ProductToCreateRequest productToCreateRequest) {
        return new Product(null, productToCreateRequest.getProductName(), productToCreateRequest.getType(),
                productToCreateRequest.getBrand(), productToCreateRequest.getColor(), productToCreateRequest.getNotes());
    }

    public ProductInfoResponse createProduct(ProductToCreateRequest productToCreateRequest) {
        Product product = productRepository.save(this.toModel(productToCreateRequest));
        return new ProductInfoResponse(product.getId(), product.getProductName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes());
    }

    public Product findById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

}
