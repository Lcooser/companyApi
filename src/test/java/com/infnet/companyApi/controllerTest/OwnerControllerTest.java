package com.infnet.companyApi.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.companyApi.controller.OwnerController;
import com.infnet.companyApi.dto.OwnerDto;
import com.infnet.companyApi.service.OwnerService;
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

class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void shouldCreateOwnerSuccessfully() throws Exception {
        OwnerDto ownerDto = createOwnerDto();
        when(ownerService.createOwner(any(OwnerDto.class))).thenReturn(ownerDto);

        mockMvc.perform(post("/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ownerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllOwnersSuccessfully() throws Exception {
        List<OwnerDto> owners = List.of(createOwnerDto(), createOwnerDto());
        when(ownerService.getAllOwners()).thenReturn(owners);

        mockMvc.perform(get("/owner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldGetOwnerByIdSuccessfully() throws Exception {
        UUID ownerId = UUID.randomUUID();
        OwnerDto ownerDto = createOwnerDto();
        when(ownerService.getOwnerById(ownerId)).thenReturn(ownerDto);

        mockMvc.perform(get("/owner/{id}", ownerId))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ownerId.toString()));
    }

    @Test
    void shouldUpdateOwnerSuccessfully() throws Exception {
        UUID ownerId = UUID.randomUUID();
        OwnerDto ownerDto = createOwnerDto();
        when(ownerService.updateOwner(eq(ownerId), any(OwnerDto.class))).thenReturn(ownerDto);

        mockMvc.perform(put("/owner/{id}", ownerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ownerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ownerId.toString()));
    }

    @Test
    void shouldDeleteOwnerSuccessfully() throws Exception {
        UUID ownerId = UUID.randomUUID();
        doNothing().when(ownerService).deleteOwner(ownerId);

        mockMvc.perform(delete("/owner/{id}", ownerId))
                .andExpect(status().isNoContent());
    }

    private OwnerDto createOwnerDto() {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(UUID.randomUUID());
        ownerDto.setName("Test Owner");

        return ownerDto;
    }
}
