package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Product;
import com.infnet.companyApi.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(UUID id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(UUID id, ProductDto productDto);
    void deleteProduct(UUID id);


}
