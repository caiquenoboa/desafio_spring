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

    @Override
    public Product toModel(ProductToCreateRequest productToCreateRequest) {
        return new Product(null, productToCreateRequest.getProductName(), productToCreateRequest.getType(),
                productToCreateRequest.getBrand(), productToCreateRequest.getColor(), productToCreateRequest.getNotes());
    }

    @Override
    public ProductInfoResponse fromModel(Product product) {
        return new ProductInfoResponse(product.getId(), product.getProductName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes());
    }

    @Override
    public ProductInfoResponse createProduct(ProductToCreateRequest productToCreateRequest) {
        Product product = productRepository.save(this.toModel(productToCreateRequest));
        return this.fromModel(product);
    }

    @Override
    public Product findById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

}
