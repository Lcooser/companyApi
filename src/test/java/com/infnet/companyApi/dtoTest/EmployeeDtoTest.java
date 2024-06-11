package com.infnet.companyApi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDtoTest {

    @Test
    void testEmployeeDtoCreation() {

        UUID id = UUID.randomUUID();
        String name = "Test Employee";


        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        employeeDto.setName(name);


        assertEquals(id, employeeDto.getId());
        assertEquals(name, employeeDto.getName());
    }
}
