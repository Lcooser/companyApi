package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Employee;
import com.infnet.companyApi.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(UUID id);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(UUID id, EmployeeDto employeeDto);
    void deleteEmployee(UUID id);

}
