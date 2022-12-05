package com.learningmicroservices.productservice.service;

import com.learningmicroservices.productservice.dto.ProductRequest;
import com.learningmicroservices.productservice.dto.ProductResponse;
import com.learningmicroservices.productservice.model.Product;
import com.learningmicroservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ProductService {

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = new Product(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice());

        productRepository.save(product);
    }

    public List<ProductResponse> getAllProduct(){
            List<Product> products = productRepository.findAll();

            return products.stream().map((product)-> {
               return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
            }).collect(Collectors.toList());
    }

}
