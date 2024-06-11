package com.infnet.companyApi.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.companyApi.controller.CompanyController;
import com.infnet.companyApi.dto.CompanyDto;
import com.infnet.companyApi.service.CompanyService;
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

class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    void shouldCreateCompanySuccessfully() throws Exception {
        // Arrange
        CompanyDto companyDto = createCompanyDto();
        when(companyService.createCompany(any(CompanyDto.class))).thenReturn(companyDto);

        // Act & Assert
        mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(companyDto.getId().toString()));
    }


    @Test
    void shouldGetAllCompaniesSuccessfully() throws Exception {
        List<CompanyDto> companies = List.of(createCompanyDto(), createCompanyDto());
        when(companyService.getAllCompanies()).thenReturn(companies);

        mockMvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldGetCompanyByIdSuccessfully() throws Exception {
        UUID companyId = UUID.randomUUID();
        CompanyDto companyDto = createCompanyDto();
        when(companyService.getCompanyById(companyId)).thenReturn(companyDto);

        mockMvc.perform(get("/company/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(companyId.toString()));
    }

    @Test
    void shouldUpdateCompanySuccessfully() throws Exception {
        UUID companyId = UUID.randomUUID();
        CompanyDto companyDto = createCompanyDto();
        when(companyService.updateCompany(eq(companyId), any(CompanyDto.class))).thenReturn(companyDto);

        mockMvc.perform(put("/company/{id}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(companyId.toString()));
    }

    @Test
    void shouldDeleteCompanySuccessfully() throws Exception {
        UUID companyId = UUID.randomUUID();
        doNothing().when(companyService).deleteCompany(companyId);

        mockMvc.perform(delete("/company/{id}", companyId))
                .andExpect(status().isNoContent());
    }

    private CompanyDto createCompanyDto() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(UUID.randomUUID());
        companyDto.setName("Test Company");

        return companyDto;
    }
}
