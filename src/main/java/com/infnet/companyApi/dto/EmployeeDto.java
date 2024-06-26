package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {

    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp = "[0-9]{11}", message = "CPF must have 11 digits")
    private String cpf;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(min = 10, max = 14, message = "Phone number must be between 10 and 14 digits")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    private Date addmissionDate;
    private Date demissionDate;
    private String gender;
    private String occupation;
    private float salary;
    private Date birthDate;
    private UUID companyId;


}
