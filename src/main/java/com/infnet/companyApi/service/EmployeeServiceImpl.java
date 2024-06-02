package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Employee;
import com.infnet.companyApi.dto.EmployeeDto;
import com.infnet.companyApi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return convertToDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = convertToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(UUID id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setCpf(employeeDto.getCpf());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setPhone(employeeDto.getPhone());
        existingEmployee.setBirthDate(employeeDto.getBirthDate());
        existingEmployee.setSalary(employeeDto.getSalary());
        existingEmployee.setAddress(employeeDto.getAddress());
        existingEmployee.setAddmissionDate(employeeDto.getAddmissionDate());
        existingEmployee.setDemissionDate(employeeDto.getDemissionDate());
        existingEmployee.setOccupation(employeeDto.getOccupation());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setCpf(employee.getCpf());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setBirthDate(employee.getBirthDate());
        dto.setSalary(employee.getSalary());
        dto.setAddress(employee.getAddress());
        dto.setAddmissionDate(employee.getAddmissionDate());
        dto.setDemissionDate(employee.getDemissionDate());
        dto.setOccupation(employee.getOccupation());

        return dto;
    }

    private Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setCpf(dto.getCpf());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setBirthDate(dto.getBirthDate());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setAddmissionDate(dto.getAddmissionDate());
        employee.setDemissionDate(dto.getDemissionDate());
        employee.setOccupation(dto.getOccupation());


        return employee;
    }
}
