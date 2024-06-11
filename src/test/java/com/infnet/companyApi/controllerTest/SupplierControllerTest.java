package com.infnet.companyApi.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.companyApi.controller.SupplierController;
import com.infnet.companyApi.dto.SupplierDto;
import com.infnet.companyApi.service.SupplierService;
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

class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    @Test
    void shouldCreateSupplierSuccessfully() throws Exception {
        SupplierDto supplierDto = createSupplierDto();
        when(supplierService.createSupplier(any(SupplierDto.class))).thenReturn(supplierDto);

        mockMvc.perform(post("/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplierDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllSuppliersSuccessfully() throws Exception {
        List<SupplierDto> suppliers = List.of(createSupplierDto(), createSupplierDto());
        when(supplierService.getSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldGetSupplierByIdSuccessfully() throws Exception {
        UUID supplierId = UUID.randomUUID();
        SupplierDto supplierDto = createSupplierDto();
        when(supplierService.getSupplierById(supplierId)).thenReturn(supplierDto);

        mockMvc.perform(get("/suppliers/{id}", supplierId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(supplierId.toString()));
    }

    @Test
    void shouldUpdateSupplierSuccessfully() throws Exception {
        UUID supplierId = UUID.randomUUID();
        SupplierDto supplierDto = createSupplierDto();
        when(supplierService.updateSupplier(eq(supplierId), any(SupplierDto.class))).thenReturn(supplierDto);

        mockMvc.perform(put("/suppliers/{id}", supplierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplierDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(supplierId.toString()));
    }

    @Test
    void shouldDeleteSupplierSuccessfully() throws Exception {
        UUID supplierId = UUID.randomUUID();
        doNothing().when(supplierService).deleteSupplier(supplierId);

        mockMvc.perform(delete("/suppliers/{id}", supplierId))
                .andExpect(status().isNoContent());
    }

    private SupplierDto createSupplierDto() {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(UUID.randomUUID());
        supplierDto.setName("Test Supplier");

        return supplierDto;
    }
}
