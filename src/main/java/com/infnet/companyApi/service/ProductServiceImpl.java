package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Company;
import com.infnet.companyApi.domain.Product;
import com.infnet.companyApi.domain.Supplier;
import com.infnet.companyApi.dto.ProductDto;
import com.infnet.companyApi.repository.CompanyRepository;
import com.infnet.companyApi.repository.ProductRepository;

import com.infnet.companyApi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return convertToDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, UUID companyId, UUID supplierId) {
        Product product = convertToEntity(productDto);

        if (companyId != null) {
            Optional<Company> companyOptional = companyRepository.findById(companyId);
            companyOptional.ifPresent(company -> {
                product.getCompanies().add(company);
                company.getProducts().add(product);
            });
        }

        if (supplierId != null) {
            Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
            supplierOptional.ifPresent(supplier -> {
                product.getSuppliers().add(supplier);
                supplier.getProducts().add(product);
            });
        }

        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }


    @Override
    public ProductDto updateProduct(UUID id, ProductDto productDto) {
        try {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setCategory(productDto.getCategory());
            existingProduct.setCreated(productDto.getCreated());
            existingProduct.setUpdated(productDto.getUpdated());

            Product updatedProduct = productRepository.save(existingProduct);
            return convertToDto(updatedProduct);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product data", e);
        }
    }

    @Override
    public void deleteProduct(UUID id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setCreated(product.getCreated());
        dto.setUpdated(product.getUpdated());
        dto.setDescription(product.getDescription());
        return dto;
    }

    private Product convertToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setCreated(dto.getCreated());
        product.setUpdated(dto.getUpdated());
        product.setDescription(dto.getDescription());

        return product;
    }

}



