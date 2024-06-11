package com.infnet.companyApi.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.companyApi.controller.ProductController;
import com.infnet.companyApi.dto.ProductDto;
import com.infnet.companyApi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void shouldCreateProductSuccessfully() throws Exception {
        ProductDto productDto = createProductDto();
        UUID companyId = UUID.randomUUID();
        UUID supplierId = UUID.randomUUID();
        when(productService.createProduct(any(ProductDto.class), eq(companyId), eq(supplierId))).thenReturn(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldGetAllProductsSuccessfully() throws Exception {
        List<ProductDto> products = List.of(createProductDto(), createProductDto());
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldGetProductByIdSuccessfully() throws Exception {
        UUID productId = UUID.randomUUID();
        ProductDto productDto = createProductDto();
        when(productService.getProductById(productId)).thenReturn(productDto);

        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId.toString()));
    }

    @Test
    void shouldUpdateProductSuccessfully() throws Exception {
        UUID productId = UUID.randomUUID();
        ProductDto productDto = createProductDto();
        when(productService.updateProduct(eq(productId), any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId.toString()));
    }

    @Test
    void shouldDeleteProductSuccessfully() throws Exception {
        UUID productId = UUID.randomUUID();
        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isNoContent());
    }

    private ProductDto createProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(UUID.randomUUID());
        productDto.setName("Test Product");

        return productDto;
    }
}
