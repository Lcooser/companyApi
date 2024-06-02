package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter


public class EmployeeDto {

    private UUID id;

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String address;
    private Date addmissionDate;
    private Date demissionDate;
    private String gender;
    private String occupation;
    private float salary;
    private Date birthDate;

}
