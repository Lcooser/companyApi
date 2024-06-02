package com.infnet.companyApi.repository;

import com.infnet.companyApi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository  extends JpaRepository<Employee, UUID> {
}
