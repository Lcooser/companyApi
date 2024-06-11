package com.infnet.companyApi.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.companyApi.controller.EmployeeController;
import com.infnet.companyApi.dto.EmployeeDto;
import com.infnet.companyApi.service.EmployeeService;
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

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        employeeDto = createEmployeeDto();
    }

    @Test
    void shouldCreateEmployeeSuccessfully() throws Exception {
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllEmployeesSuccessfully() throws Exception {
        List<EmployeeDto> employees = List.of(employeeDto, employeeDto);
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldGetEmployeeByIdSuccessfully() throws Exception {
        UUID employeeId = UUID.randomUUID();
        when(employeeService.getEmployeeById(employeeId)).thenReturn(employeeDto);

        mockMvc.perform(get("/employee/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeId.toString()));
    }

    @Test
    void shouldUpdateEmployeeSuccessfully() throws Exception {
        UUID employeeId = employeeDto.getId();
        when(employeeService.updateEmployee(employeeId, employeeDto)).thenReturn(employeeDto);

        mockMvc.perform(put("/employee/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeId.toString()));
    }


    @Test
    void shouldDeleteEmployeeSuccessfully() throws Exception {
        UUID employeeId = UUID.randomUUID();
        doNothing().when(employeeService).deleteEmployee(employeeId);

        mockMvc.perform(delete("/employee/{id}", employeeId))
                .andExpect(status().isNoContent());
    }

    private EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(UUID.randomUUID());
        employeeDto.setName("Test Employee");

        return employeeDto;
    }
}
